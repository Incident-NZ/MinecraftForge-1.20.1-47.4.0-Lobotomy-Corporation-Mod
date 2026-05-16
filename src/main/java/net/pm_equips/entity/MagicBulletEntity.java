package net.pm_equips.entity;

import net.minecraft.world.phys.BlockHitResult;
import net.pm_equips.EntityInit;
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
    private float damage;
    private float velocity;
    private int maxLifetime = 100;

    public MagicBulletEntity(EntityType<? extends MagicBulletEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public MagicBulletEntity(Level level, LivingEntity shooter, float damage, float velocity, Vec3 direction) {
        this(EntityInit.MAGIC_BULLET.get(), level);
        this.setOwner(shooter);
        this.damage = damage;
        this.velocity = velocity;
        this.setMaxLifetime(100);

        Vec3 shootDir = direction.normalize();
        Vec3 motion = shootDir.scale(this.velocity);
        this.setDeltaMovement(motion);

        Vec3 eyePos = shooter.getEyePosition();
        Vec3 spawnPos = eyePos.add(shootDir.scale(0.8));
        this.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
        Vec3 currentDir = this.getDeltaMovement();
        if (currentDir.lengthSqr() > 0) {
            Vec3 normalized = currentDir.normalize();
            this.setDeltaMovement(normalized.scale(velocity));
        }
    }

    public void setMaxLifetime(int lifetime) {
        this.maxLifetime = lifetime;
    }

    @Override
    protected void defineSynchedData() {

    }

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

            this.level().addParticle(ParticleTypes.PORTAL,
                    pos.x, pos.y, pos.z,
                    0, 0, 0);

            if (this.tickCount % 3 == 0) {
                this.level().addParticle(ParticleTypes.REVERSE_PORTAL,
                        pos.x, pos.y + 0.1, pos.z,
                        (this.random.nextFloat() - 0.5) * 0.1, 0, (this.random.nextFloat() - 0.5) * 0.1);
            }
        }

        if (this.tickCount > this.maxLifetime) {
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity target = result.getEntity();
        if (target instanceof LivingEntity livingTarget && !target.is(Objects.requireNonNull(this.getOwner()))) {
            livingTarget.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), this.damage);
        }
        this.discard();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(0.25F, 0.25F);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}