package net.lobotomy_corporation_mod.entity;

import net.lobotomy_corporation_mod.EntityInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;

public class MagicBulletEntity extends Projectile {
    public static final int MAX_LIFETIME = 64 * 10;
    private float damage = 24.0F;

    public MagicBulletEntity(EntityType<? extends MagicBulletEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public MagicBulletEntity(Level level, LivingEntity shooter, float damage, float velocity, Vec3 direction) {
        this(EntityInit.MAGIC_BULLET.get(), level);
        this.setOwner(shooter);
        this.damage = damage;

        this.setDeltaMovement(direction.x * velocity, direction.y * velocity, direction.z * velocity);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void tick() {
        super.tick();
        this.checkInsideBlocks();
        this.updateRotation();

        if (this.level().isClientSide) {
            Vec3 pos = this.position();
            Vec3 motion = this.getDeltaMovement();

            // 💜 前方パーティクル（3つ並び♡）
            for (int i = 1; i <= 3; i++) {
                double aheadX = pos.x + motion.x * i * 0.2;
                double aheadY = pos.y + motion.y * i * 0.2;
                double aheadZ = pos.z + motion.z * i * 0.2;

                double offsetX = (this.random.nextDouble() - 0.5) * 0.1;
                double offsetY = (this.random.nextDouble() - 0.5) * 0.1;
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.1;

                this.level().addParticle(ParticleTypes.PORTAL,
                        aheadX + offsetX, aheadY + offsetY, aheadZ + offsetZ, 0.0, 0.0, 0.0);
            }

            // 💎 本体輝き
            for (int i = 0; i < 2; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 0.15;
                double offsetY = (this.random.nextDouble() - 0.5) * 0.15;
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.15;

                this.level().addParticle(ParticleTypes.PORTAL,
                        pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ, 0, 0, 0);
                this.level().addParticle(ParticleTypes.ENCHANT,
                        pos.x + offsetX, pos.y + 0.3 + offsetY, pos.z + offsetZ, 0, 0, 0);
            }

            // 🔥 後方トレイル
            if (this.tickCount % 3 == 0) {
                for (int i = 0; i < 2; i++) {
                    this.level().addParticle(ParticleTypes.PORTAL,
                            pos.x - motion.x * 0.4 + (this.random.nextDouble() - 0.5) * 0.4,
                            pos.y + (this.random.nextDouble() - 0.5) * 0.3,
                            pos.z - motion.z * 0.4 + (this.random.nextDouble() - 0.5) * 0.4,
                            0, 0.01, 0);
                }
            }
        }

        // 🎯 射程超過で消滅
        if (this.tickCount > MAX_LIFETIME) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity target && !entity.is(Objects.requireNonNull(this.getOwner()))) {
            // 💥 ProjectileWeaponItemのダメージ値を使用
            target.hurt(this.level().damageSources().magic(), this.damage);
            target.hurtMarked = true;  // サーバー同期

        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.fixed(0.3F, 0.3F);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}