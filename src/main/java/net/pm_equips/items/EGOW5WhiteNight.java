package net.pm_equips.items;

import net.pm_equips.BlockInit;
import net.pm_equips.config.CommonConfig;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EGOW5WhiteNight extends BowItem {

    public EGOW5WhiteNight() {
        super(new Properties().durability(4000).setNoRepair());
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (!(entityLiving instanceof Player player)) return;

        int useDuration = this.getUseDuration(stack) - timeLeft;
        int chargeLevel = getChargeLevel(useDuration);

        if (!level.isClientSide && chargeLevel > 0) {
            double damage = switch (chargeLevel) {
                case 1 -> 28.0 * 1.2;
                case 2 -> 28.0 * 1.5;
                case 3 -> 28.0 * 2.0;
                default -> 28.0;
            };

            Vec3 look = player.getLookAngle().normalize();
            Vec3 start = player.getEyePosition();

            Vector3f startColor = new Vector3f(1.0f, 0.0f, 0.0f);
            Vector3f endColor = new Vector3f(0.0f, 0.0f, 1.0f);
            ParticleOptions beamParticle = new DustColorTransitionOptions(startColor, endColor, 1.0f);

            Set<LivingEntity> hitEntities = new HashSet<>();

            for (int i = 0; i < 256; i++) {
                Vec3 center = start.add(look.scale(i));

                level.addParticle(beamParticle, center.x, center.y, center.z, 1, 1, 1);

                AABB hitbox = new AABB(
                        center.x - 0.5, center.y - 0.5, center.z - 0.5,
                        center.x + 0.5, center.y + 0.5, center.z + 0.5
                ).inflate(2.0);

                List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, hitbox, e -> e != player);

                for (LivingEntity target : targets) {
                    if (!hitEntities.contains(target)) {

                        if (!CommonConfig.ALLOW_FRIENDLY_FIRE.get()) {
                            if (target instanceof Player) {
                                continue;
                            }
                            if (player.isAlliedTo(target)) {
                                continue;
                            }
                        }

                        hitEntities.add(target);

                        if (target.hurt(target.damageSources().magic(), (float) damage)) {

                            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 15, 2));

                            player.heal((float) damage);

                            AABB effectBox = target.getBoundingBox().inflate(1.5, 2.5, 1.5);
                            for (double x = effectBox.minX; x <= effectBox.maxX; x += 0.5) {
                                for (double y = effectBox.minY; y <= effectBox.maxY; y += 0.5) {
                                    for (double z = effectBox.minZ; z <= effectBox.maxZ; z += 0.5) {
                                        level.addParticle(beamParticle, x, y, z, 0, 0, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ALLAY_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);

            player.awardStat(Stats.ITEM_USED.get(this));
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));

            player.stopUsingItem();
        }
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    private static int getChargeLevel(int chargeTicks) {
        if (chargeTicks >= 60) return 3;
        if (chargeTicks >= 40) return 2;
        if (chargeTicks >= 20) return 1;
        return 0;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repair) {
        return repair.is(BlockInit.BlockItems.ALEPH_PE_BOX.get());
    }
}



