package net.pm_equips.entity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PWhiteNight extends Projectile implements IEntityAdditionalSpawnData {

    private static final int LIFETIME = 40; // 40 ticks = 2 seconds
    private int ticksExisted = 0;
    private final Set<Integer> hitEntities = new HashSet<>();
    private float damage = 25.0f;
    private int weaponType = 0; // 0, 1, or 2 for different weapon models

    private static final EntityDataAccessor<Integer> TEXTURE_ID = SynchedEntityData.defineId(PWhiteNight.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> WEAPON_TYPE = SynchedEntityData.defineId(PWhiteNight.class, EntityDataSerializers.INT);
    public static final int TEXTURE_COUNT = 3;

    public PWhiteNight(EntityType<? extends PWhiteNight> type, Level level) {
        super(type, level);
    }

    public PWhiteNight(EntityType<? extends PWhiteNight> type, Level level,
                       LivingEntity shooter, Vec3 pos, float damage, int weaponType) {
        this(type, level);
        this.setOwner(shooter);
        this.setPos(pos.x, pos.y, pos.z);
        this.damage = damage;
        this.weaponType = weaponType;

        if (!level.isClientSide) {
            // choose a random texture index and sync weapon type
            int tex = level.random.nextInt(TEXTURE_COUNT);
            this.entityData.set(TEXTURE_ID, tex);
            this.entityData.set(WEAPON_TYPE, weaponType);
        }
    }

    @Override
    public void tick() {
        super.tick();
        
        if (!this.level().isClientSide) {
            ticksExisted++;
            
            // Despawn after LIFETIME
            if (ticksExisted >= LIFETIME) {
                this.discard();
                return;
            }

            // Detect collisions with entities in immediate area
            AABB hitbox = this.getBoundingBox().inflate(0.8);
            List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, hitbox, 
                e -> e != this.getOwner() && !hitEntities.contains(e.getId()));

            for (LivingEntity target : targets) {
                Entity owner = this.getOwner();
                if (target.hurt(this.damageSources().thrown(this, owner), this.damage)) {
                    hitEntities.add(target.getId());
                }
            }
        }

        // Client-side: minimal visual (actual rendering is in renderer)
        if (this.level().isClientSide) {
            // Particle effects handled by client renderer
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TEXTURE_ID, 0);
        this.entityData.define(WEAPON_TYPE, 0);
    }

    public int getTextureId() {
        return this.entityData.get(TEXTURE_ID);
    }

    public void setTextureId(int id) {
        this.entityData.set(TEXTURE_ID, id);
    }

    public int getWeaponType() {
        return this.entityData.get(WEAPON_TYPE);
    }

    public void setWeaponType(int t) {
        this.entityData.set(WEAPON_TYPE, t);
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityData.get(TEXTURE_ID));
        buffer.writeInt(this.entityData.get(WEAPON_TYPE));
    }

    @Override
    public void readSpawnData(FriendlyByteBuf buffer) {
        this.entityData.set(TEXTURE_ID, buffer.readInt());
        this.entityData.set(WEAPON_TYPE, buffer.readInt());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
