package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;
import net.pm_equips.ItemInit;
import net.pm_equips.SoundInit;
import net.pm_equips.energy.WeaponEnergyProvider;
import net.pm_equips.entity.AmmoGun;

import java.util.List;
import java.util.function.Predicate;

public class RCorpRabbitRifle extends ProjectileWeaponItem {

    private static final int MAX_ENERGY = 10000;
    private static final int ENERGY_PER_SHOT = 10;

    private static final int MAX_AMMO = 50;
    private static final int RELOAD_TICKS = 30;

    private static final float VELOCITY = 6.0F;

    private static final int COOLDOWN_TICKS = 1;

    public RCorpRabbitRifle(Properties properties) {
        super(properties.durability(2000));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(ItemInit.RIFLE_BULLET_AMMO.get());
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return super.isRepairable(ItemInit.RCORP_BATTERY.get().getDefaultInstance());
    }

    @Override
    public int getDefaultProjectileRange() {
        return 128;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {
        ItemStack gun = player.getItemInHand(hand);
        CompoundTag tag = gun.getOrCreateTag();

        int reload = tag.getInt("Reload");
        int ammo = tag.getInt("Ammo");

        if (reload > 0) {
            return InteractionResultHolder.fail(gun);
        }

        if (ammo <= 0) {

            if (!level.isClientSide) {
                player.displayClientMessage(
                        Component.literal("弾薬切れ / No Ammo"),
                        true
                );

                level.playSound(
                        null,
                        player,
                        SoundEvents.DISPENSER_FAIL,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.2F
                );
            }

            return InteractionResultHolder.fail(gun);
        }

        if (!level.isClientSide) {

            shootBullet(level, player, gun);

            tag.putInt("Ammo", ammo - 1);

            gun.hurtAndBreak(
                    1,
                    player,
                    p -> p.broadcastBreakEvent(hand)
            );

            player.awardStat(
                    Stats.ITEM_USED.get(this)
            );

            player.getCooldowns()
                    .addCooldown(this, COOLDOWN_TICKS);
        }

        return InteractionResultHolder.sidedSuccess(
                gun,
                level.isClientSide
        );
    }

    private void shootBullet(
            Level level,
            Player player,
            ItemStack gun
    ) {

        boolean poweredShot =
                getEnergy(gun) >= ENERGY_PER_SHOT;

        float damage =
                5.0F + level.random.nextInt(2);

        if (poweredShot) {
            damage *= 2.0F;
        }

        Vec3 look = player.getLookAngle();

        Vec3 spawnPos =
                player.getEyePosition()
                        .add(look.scale(0.5D));

        AmmoGun bullet =
                new AmmoGun(
                        level,
                        player,
                        damage,
                        VELOCITY,
                        look
                );

        bullet.setPos(
                spawnPos.x,
                spawnPos.y,
                spawnPos.z
        );

        bullet.setDamage(damage);
        bullet.setVelocity(VELOCITY);
        bullet.setMaxLifetime(
                getDefaultProjectileRange()
        );
        bullet.setTerrainDamageEnabled(false);
        bullet.setIgnoreInvulnerabilityFrames(true);

        level.addFreshEntity(bullet);

        if (poweredShot) {
            consumeEnergy(gun);
        }

        level.playSound(
                null,
                player.blockPosition(),
                SoundInit.GUN_SEMI.get(),
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    public void startReload(
            ItemStack stack,
            Player player
    ) {

        CompoundTag tag =
                stack.getOrCreateTag();

        int reload =
                tag.getInt("Reload");

        if (reload > 0) {
            return;
        }

        int ammo =
                tag.getInt("Ammo");

        if (ammo >= MAX_AMMO) {
            return;
        }

        boolean foundAmmo = false;

        for (ItemStack invStack :
                player.getInventory().items) {

            if (invStack.is(
                    ItemInit.RIFLE_BULLET_AMMO.get())) {

                foundAmmo = true;
                break;
            }
        }

        if (!foundAmmo) {
            return;
        }

        tag.putInt(
                "Reload",
                RELOAD_TICKS
        );

        player.level().playSound(
                null,
                player.blockPosition(),
                SoundEvents.IRON_TRAPDOOR_CLOSE,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            Entity entity,
            int slot,
            boolean selected
    ) {

        CompoundTag tag =
                stack.getOrCreateTag();

        if (!tag.contains("Ammo")) {
            tag.putInt(
                    "Ammo",
                    MAX_AMMO
            );
        }

        int reload =
                tag.getInt("Reload");

        if (reload > 0) {

            reload--;

            tag.putInt(
                    "Reload",
                    reload
            );

            if (reload <= 0 &&
                    entity instanceof Player player) {

                int ammo =
                        tag.getInt("Ammo");

                int needed =
                        MAX_AMMO - ammo;

                if (needed > 0) {

                    int loaded = 0;

                    for (ItemStack invStack :
                            player.getInventory().items) {

                        if (!invStack.is(
                                ItemInit.RIFLE_BULLET_AMMO.get())) {
                            continue;
                        }

                        while (!invStack.isEmpty()
                                && loaded < needed) {

                            invStack.shrink(1);
                            loaded++;
                        }

                        if (loaded >= needed) {
                            break;
                        }
                    }

                    tag.putInt(
                            "Ammo",
                            ammo + loaded
                    );
                }
            }
        }

        super.inventoryTick(
                stack,
                level,
                entity,
                slot,
                selected
        );
    }

    @Override
    public boolean hurtEnemy(
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker
    ) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result && !attacker.level().isClientSide()) {
            // Iフレーム無視
            target.hurtTime = 0;           // クライアント側の赤フラッシュ時間
            target.invulnerableTime = 0;   // または noDamageTicks (バージョンにより名称確認)
        }

        target.hurt(
                attacker.level()
                        .damageSources()
                        .generic(),
                5.0F
        );

        stack.hurtAndBreak(
                1,
                attacker,
                p -> p.broadcastBreakEvent(
                        EquipmentSlot.MAINHAND
                )
        );

        return true;
    }

    @Override
    public boolean isValidRepairItem(
            ItemStack stack,
            ItemStack repair
    ) {
        return repair.is(
                ItemInit.METAL_INGOT.get()
        );
    }

    @Override
    public ICapabilityProvider initCapabilities(
            ItemStack stack,
            CompoundTag nbt
    ) {
        return new WeaponEnergyProvider(
                stack,
                MAX_ENERGY
        );
    }

    private int getEnergy(
            ItemStack stack
    ) {
        return stack.getCapability(
                        ForgeCapabilities.ENERGY
                )
                .map(
                        IEnergyStorage::getEnergyStored
                )
                .orElse(0);
    }

    private void consumeEnergy(
            ItemStack stack
    ) {
        stack.getCapability(
                        ForgeCapabilities.ENERGY
                )
                .ifPresent(storage ->
                        storage.extractEnergy(
                                ENERGY_PER_SHOT,
                                false
                        ));
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

        tooltip.add(
                Component.literal(
                        "FE: "
                                + getEnergy(stack)
                                + " / "
                                + MAX_ENERGY
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
