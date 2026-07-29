package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.TooltipFlag;
import net.pm_equips.EntityInit;
import net.pm_equips.ItemInit;
import net.pm_equips.SoundInit;
import net.pm_equips.entity.PBulletLARV;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class WeaponRolandLogicHG extends ProjectileWeaponItem {

	private static final int MAX_AMMO = 8;
	private static final int RELOAD_TICKS = 40; // 2 seconds
	private static final int COOLDOWN_TICKS = 20; // 1 second between shots
	private static final float VELOCITY = 8.0f;

	public WeaponRolandLogicHG(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack gun = player.getItemInHand(hand);

		if (player.getCooldowns().isOnCooldown(this)) {
			return InteractionResultHolder.fail(gun);
		}

		int ammo = gun.getOrCreateTag().getInt("Ammo");
		int reload = gun.getOrCreateTag().getInt("Reload");

		if (reload > 0) {
			return InteractionResultHolder.fail(gun);
		}

		if (ammo <= 0) {
			if (!level.isClientSide) {
				player.displayClientMessage(Component.literal("弾薬切れ / No Ammo"), true);
				level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
			}
			return InteractionResultHolder.fail(gun);
		}

		if (level.isClientSide) {
			player.level().playSound(
					null,
					player.blockPosition(),
					SoundInit.GUN_ROLAND_REVOLVER.get(),
					SoundSource.PLAYERS,
					1.0F,
					1.0F
			);
			return InteractionResultHolder.success(gun);
		}

		// server-side: spawn projectile
		Vec3 look = player.getLookAngle();
		float damage = 4 + player.getRandom().nextInt(5); // 4..8

		PBulletLARV bullet = new PBulletLARV(EntityInit.BULLET_LARV.get(), level);
		bullet.initFromShooter(player, damage, VELOCITY, look);
		level.addFreshEntity(bullet);

		gun.getOrCreateTag().putInt("Ammo", ammo - 1);

		player.level().playSound(
				null,
				player.blockPosition(),
				SoundInit.GUN_ROLAND_REVOLVER.get(),
				SoundSource.PLAYERS,
				1.0F,
				1.0F
		);

		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

		gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

		return InteractionResultHolder.success(gun);
	}

	// Rキーリロード開始 (弾が残っていても可能、残存弾は捨てずに継ぎ足す)
	public void startReload(ItemStack stack, Player player) {
		int reload = stack.getOrCreateTag().getInt("Reload");
		if (reload > 0) return;

		int ammo = stack.getOrCreateTag().getInt("Ammo");
		if (ammo >= MAX_AMMO) return;

		boolean hasAmmo = false;
		for (ItemStack invStack : player.getInventory().items) {
			if (invStack.is(ItemInit.P_BULLET_LARV.get())) {
				hasAmmo = true;
				break;
			}
		}

		if (!hasAmmo) return;

		stack.getOrCreateTag().putInt("Reload", RELOAD_TICKS);
		player.level().playSound(null, player.blockPosition(), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.PLAYERS, 1.0F, 1.0F);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
		if (!stack.getOrCreateTag().contains("Ammo")) {
			stack.getOrCreateTag().putInt("Ammo", MAX_AMMO);
		}

		int reload = stack.getOrCreateTag().getInt("Reload");

		if (reload > 0) {
			reload--;
			stack.getOrCreateTag().putInt("Reload", reload);

			if (reload <= 0 && entity instanceof Player player) {
				int ammo = stack.getOrCreateTag().getInt("Ammo");
				int needed = MAX_AMMO - ammo;
				if (needed <= 0) return;

				int loaded = 0;
				for (ItemStack invStack : player.getInventory().items) {
					if (invStack.is(ItemInit.P_BULLET_LARV.get())) {
						while (!invStack.isEmpty() && loaded < needed) {
							invStack.shrink(1);
							loaded++;
						}
					}
					if (loaded >= needed) break;
				}

				stack.getOrCreateTag().putInt("Ammo", ammo + loaded);
			}
		}

		super.inventoryTick(stack, level, entity, slot, selected);
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return stack -> stack.is(ItemInit.P_BULLET_LARV.get());
	}

	@Override
	public int getDefaultProjectileRange() {
		return 32;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			Level level,
			List<Component> tooltip,
			TooltipFlag flag
	) {

		CompoundTag tag =
				stack.getOrCreateTag();

		tooltip.add(
				Component.literal(
						"Ammo: "
								+ tag.getInt("Ammo")
								+ " / "
								+ MAX_AMMO
				)
		);

		super.appendHoverText(
				stack,
				level,
				tooltip,
				flag
		);
	}
}
