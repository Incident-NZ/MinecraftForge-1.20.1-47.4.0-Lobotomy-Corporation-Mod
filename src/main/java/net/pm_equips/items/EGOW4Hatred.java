package net.pm_equips.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.pm_equips.entity.EGOHatredMagicProjectile;

import java.util.function.Predicate;

public class EGOW4Hatred extends ProjectileWeaponItem {
    public EGOW4Hatred() {
        super(new Properties().durability(3000));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            EGOHatredMagicProjectile projectile = new EGOHatredMagicProjectile(level, player);
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
