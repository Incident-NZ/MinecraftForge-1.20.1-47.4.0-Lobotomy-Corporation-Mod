package net.pm_equips.items;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;
import net.pm_equips.PMEquipsMain;

public class EGOW4Swan extends SwordItem {
    private static final float REFLECTION_CHANCE = 0.1F;

    public EGOW4Swan() {
        super(new CustomTier(), 6, -1.8F, new Properties().durability(3000));
    }

    @Mod.EventBusSubscriber(modid = PMEquipsMain.MOD_ID)
    public static class SwanEvents {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onLivingDamage(LivingDamageEvent event) {
            if (!(event.getEntity() instanceof Player player)
                    || player.level().isClientSide()
                    || event.getAmount() <= 0.0F
                    || event.getSource().is(DamageTypes.THORNS)) {
                return;
            }

            ItemStack heldItem = player.getMainHandItem();
            if (!heldItem.is(ItemInit.W4_SWAN.get())) {
                return;
            }

            if (!(event.getSource().getEntity() instanceof LivingEntity attacker)
                    || attacker == player
                    || !attacker.isAlive()
                    || player.getRandom().nextFloat() >= REFLECTION_CHANCE) {
                return;
            }

            attacker.hurt(player.damageSources().thorns(player), event.getAmount());
        }
    }

    private static class CustomTier implements Tier {
        @Override public int getUses() { return 3000; }
        @Override public float getSpeed() { return 4.0F; }
        @Override public float getAttackDamageBonus() { return 0.0F; }
        @Override public int getLevel() { return 0; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() {
            return Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get());
        }
    }
}
