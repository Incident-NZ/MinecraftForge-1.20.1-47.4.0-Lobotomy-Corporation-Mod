package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.entity.W5StarProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class W5Star extends Item {
    public W5Star() {
        super(new Properties().stacksTo(16));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // 前方に3発発射（中央・左・右）
            shootProjectile(level, player, 0.0f);   // 中央
            shootProjectile(level, player, -10.0f); // 左
            shootProjectile(level, player, 10.0f);  // 右

            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private void shootProjectile(Level level, Player player, float angleOffset) {
        W5StarProjectile projectile = new W5StarProjectile(level, player);

        projectile.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot() + angleOffset, 0.0F, 2.0F, 1.0F);

        // 雪玉の2倍飛距離 = 初速を上げる
        projectile.setDeltaMovement(projectile.getDeltaMovement().scale(2.0));

        level.addFreshEntity(projectile);
    }
}
