package net.pm_equips.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.pm_equips.BlockInit;
import net.pm_equips.config.CommonConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.pm_equips.entity.WhiteNightProjectile;
import net.pm_equips.EntityInit;

import java.util.UUID;

public class EGOW5WhiteNight extends SwordItem {

    private static final UUID REACH_UUID = UUID.randomUUID();
    private static final AttributeModifier REACH_MODIFIER =
            new AttributeModifier(REACH_UUID, "white_night_reach", 3.0, AttributeModifier.Operation.ADDITION);

    // Cooldown tracking per player
    private static final java.util.Map<UUID, Long> LAST_RANGED_ATTACK = new java.util.WeakHashMap<>();
    public EGOW5WhiteNight() {
        super(new CustomTier(), 50, -3.8f, new Properties().durability(4000).rarity(Rarity.EPIC));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isClientSide || !(entity instanceof Player player)) return;

        boolean isHolding = selected && player.getMainHandItem() == stack;

        AttributeInstance reachAttr = player.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (reachAttr != null) {
            if (isHolding && !reachAttr.hasModifier(REACH_MODIFIER)) {
                reachAttr.addTransientModifier(REACH_MODIFIER);
            } else if (!isHolding && reachAttr.hasModifier(REACH_MODIFIER)) {
                reachAttr.removeModifier(REACH_MODIFIER);
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result && !attacker.level().isClientSide()) {
            // Apply Absorption (HP 20 = 10 absorption hearts)
            if (attacker instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 0, false, false));
            }
        }
        return result;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Use ProjectileUtil to pick an entity in the look direction
            Vec3 eyePos = player.getEyePosition();
            Vec3 lookDir = player.getLookAngle();
            Vec3 end = eyePos.add(lookDir.scale(64.0D));

            AABB box = player.getBoundingBox().expandTowards(lookDir.scale(64.0D)).inflate(1.0D);

            EntityHitResult entityHit = net.minecraft.world.entity.projectile.ProjectileUtil.getEntityHitResult(
                    level,
                    player,
                    eyePos,
                    end,
                    box,
                    e -> e instanceof LivingEntity
                            && e.isAlive()
                            && e != player
                            && (CommonConfig.ALLOW_FRIENDLY_FIRE.get() || !(e instanceof Player) && !player.isAlliedTo((LivingEntity) e))
            );

            if (entityHit != null && entityHit.getEntity() instanceof LivingEntity target) {
                // apply a small cooldown (10 ticks = 0.5s)
                player.getCooldowns().addCooldown(this, 10);

                // perform ranged attack
                fireRangedAttack(level, player, target, itemStack);

                // award stat
                player.awardStat(net.minecraft.stats.Stats.ITEM_USED.get(this));

                return InteractionResultHolder.success(itemStack);
            }
        }

        return InteractionResultHolder.pass(itemStack);
    }

    private void fireRangedAttack(Level level, Player player, LivingEntity target, ItemStack itemStack) {
        // Calculate random ranged damage (22-28)
        int damage = 22 + level.random.nextInt(7);

        // Generate 12 projectiles in 3x3 area around target (on ground)
        Vec3 targetPos = target.position();
        int projectilesPerWeapon = 4; // 3 weapon types x 4 = 12 total

        for (int weaponType = 0; weaponType < 3; weaponType++) {
            for (int i = 0; i < projectilesPerWeapon; i++) {
                // Random position in 3x3 area (xz plane, ground level)
                double randomX = targetPos.x + (level.random.nextDouble() - 0.5) * 3;
                double randomZ = targetPos.z + (level.random.nextDouble() - 0.5) * 3;
                double spawnY = targetPos.y;

                // Find ground level
                net.minecraft.core.BlockPos checkPos = new net.minecraft.core.BlockPos((int) randomX, (int) spawnY - 1, (int) randomZ);
                while (spawnY > 0 && level.getBlockState(checkPos).isCollisionShapeFullBlock(level, checkPos)) {
                    spawnY--;
                    checkPos = new net.minecraft.core.BlockPos((int) randomX, (int) spawnY - 1, (int) randomZ);
                }

                Vec3 spawnPos = new Vec3(randomX, spawnY + 1.5, randomZ);

                WhiteNightProjectile projectile = new WhiteNightProjectile(
                        EntityInit.WHITE_NIGHT_PROJECTILE.get(),
                        level,
                        player,
                        spawnPos,
                        (float) damage,
                        weaponType
                );

                // Add upward velocity for "thrust from ground" effect
                projectile.setDeltaMovement(0, 0.2, 0);

                level.addFreshEntity(projectile);
            }
        }

        // Apply effects to target
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false)); // 5 sec, level III (value 2)
        target.hurt(target.damageSources().magic(), (float) damage);

        // Play sound
        level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.WARDEN_HURT, SoundSource.PLAYERS, 1.0F, 1.2F);

        // Damage weapon
        itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 4000;
        }

        @Override
        public float getSpeed() {
            return 0.2f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 10.0f; // 50-60 damage = 10 base + 50 sword damage
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get());
        }
    }
}



