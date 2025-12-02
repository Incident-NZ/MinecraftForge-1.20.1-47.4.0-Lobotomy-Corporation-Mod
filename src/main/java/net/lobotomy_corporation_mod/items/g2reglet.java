package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.CapabilitiesInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.UUID;

public class g2reglet extends Item implements ICurioItem {
    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("12345678-1234-1234-1234-123456789abc");

    public g2reglet(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("2_mouse_1");
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH))
                .addTransientModifier(new AttributeModifier(
                        HEALTH_MODIFIER_UUID,
                        "E.G.O Gift Reglet",
                        2.0D,
                        AttributeModifier.Operation.ADDITION
                ));

        entity.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(mental -> {
            int current = mental.getMentalHealth();
            mental.setMentalHealth(current + 2);
        });
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        Objects.requireNonNull(entity.getAttribute(Attributes.MAX_HEALTH))
                .removeModifier(HEALTH_MODIFIER_UUID);

        entity.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(mental -> {
            int current = mental.getMentalHealth();
            mental.setMentalHealth(Math.min(current, 20));  // 20超えてる分を全部削る
        });
    }
}
