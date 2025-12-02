package net.lobotomy_corporation_mod.entity;

import net.lobotomy_corporation_mod.EntityInit;
import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.config.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class W5StarProjectile extends ThrowableItemProjectile {
    private static final float BASE_DAMAGE = 12.0f;

    public W5StarProjectile(EntityType<? extends W5StarProjectile> type, Level level) {
        super(type, level);
    }

    public W5StarProjectile(Level level, LivingEntity thrower) {
        super(EntityInit.W5_SOUND_OF_A_STAR_PROJECTILE.get(), thrower, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.W5_SOUND_OF_A_STAR.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level().isClientSide) return;

        Entity target = result.getEntity();
        Entity owner = this.getOwner();

        if (!(target instanceof LivingEntity living)) return;

        // === フレンドリーファイア制御 ===
        if (!Config.ALLOW_FRIENDLY_FIRE.get()) {
            if (owner instanceof Player player) {
                if (target instanceof Player) return; // プレイヤーへの攻撃無効
                if (player.isAlliedTo(target)) return; // 味方モブも無効
            }
        }

        float damage = BASE_DAMAGE;

        if (owner instanceof LivingEntity livingOwner) {
            float hp = livingOwner.getHealth();

            if (hp >= 10) {
                damage *= 2.0f; // HP10以上 → 2倍
            } else if (hp < 5) {
                damage *= 0.25f; // HP5以下 → 75%減
            } else {
                damage *= 0.5f; // HP10未満 → 半減
            }
        }

        living.hurt(this.damageSources().thrown(this, owner), damage);
        this.discard(); // 命中後削除
    }
}
