package net.lobotomy_corporation_mod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;

public class W4LamentR extends Item {

    private static final String AMMO_TAG = "solemn_lament_ammo";
    private static final int MAX_AMMO = 16;
    private static final int RELOAD_TICKS = 30;
    private static final float DAMAGE = 2.0f;
    private static final int RANGE = 128;

    public W4LamentR() {
        super(new Properties().durability(3000).setNoRepair());
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 50;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) return;

        int useTicks = this.getUseDuration(stack) - timeLeft;
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("Reloaded", true);
        stack.setTag(tag);

        if (useTicks >= RELOAD_TICKS) {
            setAmmo(stack, MAX_AMMO);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        if (!(main.getItem() instanceof W4LamentR && off.getItem() instanceof W4LamentL)) {
            return InteractionResultHolder.fail(main);
        }

        ItemStack stack = player.getItemInHand(hand);
        int ammo = getAmmo(stack);

        if (ammo > 0) {
            if (!level.isClientSide) {
                shoot(level, player);
                setAmmo(stack, ammo - 1);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            }
            return InteractionResultHolder.success(stack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
    }

    private void shoot(Level level, Player player) {
        Vec3 start = player.getEyePosition();
        Vec3 look = player.getLookAngle();

        for (int i = 0; i < RANGE; i++) {
            Vec3 pos = start.add(look.scale(i * 0.5));
            level.addParticle(ParticleTypes.CRIT, pos.x, pos.y, pos.z, 0, 0, 0);

            AABB box = new AABB(pos.subtract(0.5, 0.5, 0.5), pos.add(0.5, 0.5, 0.5));
            List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, box, e -> e != player);

            for (LivingEntity target : targets) {
                target.hurt(target.damageSources().playerAttack(player), DAMAGE);
                return;
            }
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private int getAmmo(ItemStack stack) {
        return stack.getOrCreateTag().getInt(AMMO_TAG);
    }

    private void setAmmo(ItemStack stack, int ammo) {
        stack.getOrCreateTag().putInt(AMMO_TAG, Math.max(0, Math.min(MAX_AMMO, ammo)));
    }
}
