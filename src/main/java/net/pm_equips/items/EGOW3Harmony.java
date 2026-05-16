package net.pm_equips.items;

import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;
import net.pm_equips.config.Config;
import net.pm_equips.entity.PBulletExp;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class EGOW3Harmony extends ProjectileWeaponItem {
    private static final float DAMAGE = 50.0f;
    private static final float VELOCITY = 4.0f;
    private static final int RANGE = 128;
    private static final int COOLDOWN_TICKS = 120;
    private static final float EXPLOSION_POWER = 30.0f;

    public EGOW3Harmony(Properties properties) {
        super(properties.durability(2000));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(ItemInit.EXPLOSIVE_BULLET_AMMO.get());
    }

    @Override
    public int getDefaultProjectileRange() {
        return RANGE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);

        if (!hasAmmo(player)) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("弾薬切れ / No Ammo"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(gun);
        }

        if (level.isClientSide) {
            level.playSound(player, player.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.2F, 0.8F);
            return InteractionResultHolder.consume(gun);
        }

        shootBullet(level, player);
        consumeAmmo(player);

        gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        player.awardStat(Stats.ITEM_USED.get(this));
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        return InteractionResultHolder.consume(gun);
    }

    private boolean hasAmmo(Player player) {
        return player.getInventory().contains(new ItemStack(ItemInit.EXPLOSIVE_BULLET_AMMO.get()));
    }

    private void consumeAmmo(Player player) {
        if (!player.getAbilities().instabuild) {
            player.getInventory().clearOrCountMatchingItems(
                    stack -> stack.is(ItemInit.EXPLOSIVE_BULLET_AMMO.get()),
                    1, player.inventoryMenu.getCraftSlots()
            );
        }
    }

    private void shootBullet(Level level, Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition();
        Vec3 spawnPos = eyePos.add(look.scale(0.5));
        Vec3 endPos = eyePos.add(look.scale(getDefaultProjectileRange()));

        EntityHitResult entityHit = null;
        if (!level.isClientSide) {
            entityHit = ProjectileUtil.getEntityHitResult(
                    level,
                    player,
                    eyePos,
                    endPos,
                    player.getBoundingBox().expandTowards(look.scale(getDefaultProjectileRange())).inflate(1.0D),
                    (e) -> e != player && e instanceof LivingEntity && e.isAlive()
            );
        }

        Vec3 explosionCenter;
        if (entityHit != null) {
            explosionCenter = entityHit.getEntity().position();
        } else {
            explosionCenter = endPos;
        }

        if (!level.isClientSide) {
            handleExplosion(level, player, explosionCenter.x, explosionCenter.y, explosionCenter.z);
        }

        PBulletExp bullet = new PBulletExp(level, player, DAMAGE, VELOCITY, look);
        bullet.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        bullet.setDamage(DAMAGE);
        bullet.setVelocity(VELOCITY);
        bullet.setMaxLifetime(getDefaultProjectileRange());
        level.addFreshEntity(bullet);

        level.playSound(null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.2F, 0.8F);
    }

    private void handleExplosion(Level lvl, Player shooter, double x, double y, double z) {
        boolean allowTerrain = Config.ALLOW_TERRAIN_DAMAGE.get();
        boolean allowFriendly = Config.ALLOW_FRIENDLY_FIRE.get();

        double radius = Math.max(1.0, EXPLOSION_POWER);

        if (allowTerrain) {
            Map<Player, Float> savedPlayerHealth = new HashMap<>();
            if (!allowFriendly) {
                List<Player> players = lvl.getEntitiesOfClass(
                        Player.class,
                        new AABB(x, y, z, x, y, z).inflate(radius),
                        p -> p != shooter && p.isAlive()
                );
                for (Player p : players) {
                    savedPlayerHealth.put(p, p.getHealth());
                }
            }

            Explosion explosion = lvl.explode(shooter, x, y, z, EXPLOSION_POWER, Level.ExplosionInteraction.TNT);

            if (!allowFriendly) {
                for (Map.Entry<Player, Float> e : savedPlayerHealth.entrySet()) {
                    Player p = e.getKey();
                    if (p.isAlive()) {
                        p.setHealth(e.getValue());
                        p.invulnerableTime = 0;
                    }
                }
            }
        } else {
            Explosion explosion = lvl.explode(shooter, x, y, z, EXPLOSION_POWER, Level.ExplosionInteraction.NONE);

            AABB area = new AABB(x, y, z, x, y, z).inflate(radius);
            List<LivingEntity> targets = lvl.getEntitiesOfClass(LivingEntity.class, area, e -> e.isAlive() && e != shooter);
            for (LivingEntity t : targets) {
                if (t instanceof Player && !allowFriendly) {
                    continue;
                }
                t.hurt(lvl.damageSources().explosion(explosion), DAMAGE);
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurt(attacker.level().damageSources().explosion(null), DAMAGE);
        stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repair) {
        return repair.is(BlockInit.BlockItems.HE_PE_BOX.get());
    }
}
