package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class W2Match extends CrossbowItem {

    private static final String AMMO_TAG = "fourth_match_ammo";
    private static final int MAX_AMMO = 1;
    private static final int RELOAD_TICKS = 60; // 3秒（20tick * 3）

    public W2Match() {
        super(new Properties().durability(2000).setNoRepair());
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return RELOAD_TICKS;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (!(entityLiving instanceof Player player)) return;

        int useTicks = this.getUseDuration(stack) - timeLeft;
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("Reloaded", true);
        stack.setTag(tag);

        if (useTicks >= RELOAD_TICKS && getAmmo(stack) == 0) {
            setAmmo(stack, MAX_AMMO);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ARMOR_EQUIP_IRON, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int ammo = getAmmo(stack);

        if (ammo > 0) {
            if (!level.isClientSide) {
                shoot(level, player, stack);
                setAmmo(stack, 0);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            }
            return InteractionResultHolder.success(stack);
        }

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    private void shoot(Level level, Player player, ItemStack stack) {
        Vec3 direction = player.getLookAngle();
        Vec3 spawnPos = player.getEyePosition().add(direction.scale(1.0));

        CustomFireball fireball = new CustomFireball(level, player, direction.x, direction.y, direction.z);
        fireball.setPos(spawnPos);
        level.addFreshEntity(fireball);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private int getAmmo(ItemStack stack) {
        return stack.getOrCreateTag().getInt(AMMO_TAG);
    }

    private void setAmmo(ItemStack stack, int ammo) {
        stack.getOrCreateTag().putInt(AMMO_TAG, Math.max(0, Math.min(MAX_AMMO, ammo)));
    }

    // === カスタムファイアボール ===
    public static class CustomFireball extends SmallFireball {
        public CustomFireball(Level level, LivingEntity shooter, double x, double y, double z) {
            super(level, shooter, x, y, z);
        }

        @Override
        protected void onHit(HitResult result) {
            super.onHit(result);
            if (!this.level().isClientSide && result.getType() == HitResult.Type.BLOCK) {
                Vec3 pos = result.getLocation();

                // 🔹 Configを参照して地形破壊を制御
                Level.ExplosionInteraction interaction =
                        Config.ALLOW_TERRAIN_DAMAGE.get()
                                ? Level.ExplosionInteraction.BLOCK   // ブロック破壊あり
                                : Level.ExplosionInteraction.NONE;   // ダメージはあるがブロック破壊なし

                // 爆発
                level().explode(this, pos.x, pos.y, pos.z, 30.0f, interaction);

                // 🔹 Configがtrueの時だけ炎を撒き散らす
                if (Config.ALLOW_TERRAIN_DAMAGE.get()) {
                    for (int dx = -5; dx <= 5; dx++) {
                        for (int dy = -5; dy <= 5; dy++) {
                            for (int dz = -5; dz <= 5; dz++) {
                                BlockPos firePos = this.blockPosition().offset(dx, dy, dz);
                                BlockState state = level().getBlockState(firePos);
                                if (state.isAir()) {
                                    level().setBlock(firePos, Blocks.FIRE.defaultBlockState(), 15);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



