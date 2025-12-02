package net.lobotomy_corporation_mod.Interface;

import net.lobotomy_corporation_mod.items.MagicBulletItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class ProjectileWeaponItem extends Item {
    protected final ResourceLocation bulletType;
    protected final float velocity;
    protected final float damage;

    public ProjectileWeaponItem(Properties properties, ResourceLocation bulletType, float damage, float velocity) {
        super(properties);
        this.bulletType = bulletType;
        this.damage = damage;
        this.velocity = velocity;
    }

    public abstract void shootBullet(Level level, Player player);

    protected boolean hasAmmo(Player player, ResourceLocation bulletType) {
        for (ItemStack stack : player.getInventory().items) {
            if (isValidAmmo(stack, bulletType)) return true;
        }
        return isValidAmmo(player.getOffhandItem(), bulletType);
    }

    protected boolean isValidAmmo(ItemStack stack, ResourceLocation bulletType) {
        return !stack.isEmpty() && stack.getItem() instanceof MagicBulletItem ammo
                && ammo.getBulletType().equals(bulletType);
    }

    protected void consumeAmmo(Player player, ResourceLocation bulletType) {
        for (ItemStack stack : player.getInventory().items) {
            if (isValidAmmo(stack, bulletType)) {
                stack.shrink(1);
                return;
            }
        }
        if (isValidAmmo(player.getOffhandItem(), bulletType)) {
            player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        }
    }

    protected void triggerMuzzleFlash(Level level, Player player) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle().normalize();
        Vec3 muzzlePos = eyePos.add(look.scale(0.3));

        for (int i = 0; i < 15; i++) {
            level.addParticle(ParticleTypes.FLAME, muzzlePos.x, muzzlePos.y, muzzlePos.z,
                    (level.random.nextDouble() - 0.5) * 0.6,
                    level.random.nextDouble() * 0.4,
                    (level.random.nextDouble() - 0.5) * 0.6);
            level.addParticle(ParticleTypes.PORTAL, muzzlePos.x, muzzlePos.y, muzzlePos.z,
                    (level.random.nextDouble() - 0.5) * 0.4,
                    level.random.nextDouble() * 0.3,
                    (level.random.nextDouble() - 0.5) * 0.4);
        }
    }
}
