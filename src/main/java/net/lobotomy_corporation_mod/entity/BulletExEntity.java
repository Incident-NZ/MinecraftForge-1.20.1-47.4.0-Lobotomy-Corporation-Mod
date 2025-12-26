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

public class BulletExEntity extends Projectile {
    private float damage = 0f;
    private int maxLifetime = 100;
    private static final float EXPLOSION_POWER = 20.0f; // TNT (4.0f) * 5 = 20.0f

    public BulletExEntity(EntityType<? extends BulletExEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public BulletExEntity(Level level, LivingEntity shooter, float damage, float velocity, Vec3 direction) {
        this(EntityInit.BULLET_EX.get(), level);
        this.setOwner(shooter);
        this.damage = damage;
        this.setMaxLifetime(100);

        Vec3 shootDir = direction.normalize();
        Vec3 motion = shootDir.scale(velocity);
        this.setDeltaMovement(motion);

        Vec3 eyePos = shooter.getEyePosition();
        Vec3 spawnPos = eyePos.add(shootDir.scale(0.8));
        this.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public void tick() {
        super.tick();

        Vec3 motion = this.getDeltaMovement();
        if (motion.lengthSqr() > 0) {
            this.setPos(this.getX() + motion.x, this.getY() + motion.y, this.getZ() + motion.z);
        }

        this.checkInsideBlocks();

        if (this.level().isClientSide) {
            Vec3 pos = this.position();
            this.level().addParticle(ParticleTypes.SMOKE,
                    pos.x, pos.y, pos.z,
                    0, 0, 0);

            if (this.tickCount % 3 == 0) {
                this.level().addParticle(ParticleTypes.LARGE_SMOKE,
                        pos.x, pos.y + 0.1, pos.z,
                        (this.random.nextFloat() - 0.5) * 0.1, 0, (this.random.nextFloat() - 0.5) * 0.1);
            }
        }

        if (this.tickCount > this.maxLifetime) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (!this.level().isClientSide) {
            explodeAt(this.getX(), this.getY(), this.getZ());
        }
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            explodeAt(result.getEntity().getX(), result.getEntity().getY(), result.getEntity().getZ());
        }
        this.discard();
    }

    private void explodeAt(double x, double y, double z) {
        Level lvl = this.level();
        if (!lvl.isClientSide) {
            lvl.explode(this.getOwner(), x, y, z, EXPLOSION_POWER, Level.ExplosionInteraction.TNT);
        }
    }


    public void setDamage(float damage) { this.damage = damage; }
    public void setVelocity(float velocity) {
        Vec3 dir = this.getDeltaMovement().normalize();
        if (dir.lengthSqr() > 0) {
            this.setDeltaMovement(dir.scale(velocity));
        }
    }
    public void setMaxLifetime(int lifetime) { this.maxLifetime = lifetime; }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        // 当たり判定を 1.5F にする
        return EntityDimensions.fixed(1.5F, 1.5F);
    }
}
