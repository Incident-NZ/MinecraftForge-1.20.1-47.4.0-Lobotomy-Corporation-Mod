package net.pm_equips.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.pm_equips.entity.EGOHatredMagicP;

import java.util.function.Predicate;

public class EGOW4Hatred extends ProjectileWeaponItem {
    public EGOW4Hatred() {
        super(new Properties().durability(3000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result && !attacker.level().isClientSide()) {
            // Iフレーム無視
            target.hurtTime = 0;           // クライアント側の赤フラッシュ時間
            target.invulnerableTime = 0;   // または noDamageTicks (バージョンにより名称確認)
        }
        return result;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            EGOHatredMagicP projectile = new EGOHatredMagicP(level, player);
            level.addFreshEntity(projectile);

            stack.hurtAndBreak(1, player, ignored -> player.broadcastBreakEvent(hand));
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 128;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> false;
    }
}
