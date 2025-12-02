package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.CapabilitiesInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class g1penitence extends Item implements ICurioItem {

    public g1penitence(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("1_head_1");
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        entity.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(mental -> {
            int current = mental.getMentalHealth();
            mental.setMentalHealth(current + 2);
        });
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        entity.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(mental -> {
            int current = mental.getMentalHealth();
            mental.setMentalHealth(Math.min(current, 20));  // 20超えてる分を全部削る
        });
    }
}
