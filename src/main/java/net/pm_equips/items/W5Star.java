package net.pm_equips.items;

import net.pm_equips.entity.W5StarProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class W5Star extends Item {
    public W5Star() {
        super(new Properties().durability(4000));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            shootProjectile(level, player, 0.0f);
            shootProjectile(level, player, -10.0f);
            shootProjectile(level, player, 10.0f);

            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private void shootProjectile(Level level, Player player, float angleOffset) {
        W5StarProjectile projectile = new W5StarProjectile(level, player);

        projectile.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot() + angleOffset, 0.0F, 2.0F, 1.0F);
        projectile.setDeltaMovement(projectile.getDeltaMovement().scale(2.0));

        level.addFreshEntity(projectile);
    }
}
