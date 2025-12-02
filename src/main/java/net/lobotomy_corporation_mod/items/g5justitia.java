package net.lobotomy_corporation_mod.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.UUID;

public class g5justitia extends Item implements ICurioItem {
    private static final UUID MOVESPEED_MODIFIER_UUID = UUID.fromString("72345678-1234-1234-1234-123456789abc");
    private static final UUID ATTACKSPEED_MODIFIER_UUID = UUID.fromString("82345678-1234-1234-1234-123456789abc");
    public g5justitia(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("5_eye");
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED))
                .addTransientModifier(new AttributeModifier(
                        MOVESPEED_MODIFIER_UUID,
                        "E.G.O Gift Justitia",
                        2.0D,
                        AttributeModifier.Operation.ADDITION
                ));
        Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_SPEED))
                .addTransientModifier(new AttributeModifier(
                        ATTACKSPEED_MODIFIER_UUID,
                        "E.G.O Gift Justitia",
                        2.0D,
                        AttributeModifier.Operation.ADDITION
                ));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED))
                .removeModifier(MOVESPEED_MODIFIER_UUID);
        Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_SPEED))
                .removeModifier(ATTACKSPEED_MODIFIER_UUID);
    }
}
