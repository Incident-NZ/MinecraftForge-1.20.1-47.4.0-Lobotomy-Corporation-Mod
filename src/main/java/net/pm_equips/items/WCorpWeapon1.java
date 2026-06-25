package net.pm_equips.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.pm_equips.energy.WcorpWeaponProvider;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class WCorpWeapon1 extends SwordItem {

    private static final UUID DAMAGE_UUID =
            UUID.fromString("a5f14bc8-fbf9-45d4-90b2-4c7763ef4d1a");

    private static final UUID SPEED_UUID =
            UUID.fromString("42d4c9f4-88d0-4b79-a0c5-c2866a50afcb");

    public WCorpWeapon1(
            Tier tier,
            int damage,
            float speed,
            Properties properties) {

        super(tier, damage, speed, properties);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(
            ItemStack stack,
            @Nullable net.minecraft.nbt.CompoundTag nbt) {

        return new WcorpWeaponProvider();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {

        return stack.getCapability(ForgeCapabilities.ENERGY)
                .map(storage ->
                        Math.round(
                                13.0F *
                                        storage.getEnergyStored() /
                                        storage.getMaxEnergyStored()
                        ))
                .orElse(0);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x00FFFF;
    }

    @Override
    public boolean hurtEnemy(
            ItemStack stack,
            net.minecraft.world.entity.LivingEntity target,
            net.minecraft.world.entity.LivingEntity attacker) {

        stack.getCapability(ForgeCapabilities.ENERGY)
                .ifPresent(storage ->
                        storage.extractEnergy(500, false));

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public Multimap<Attribute, AttributeModifier>
    getDefaultAttributeModifiers(EquipmentSlot slot) {

        ImmutableMultimap.Builder<Attribute, AttributeModifier>
                builder = ImmutableMultimap.builder();

        builder.putAll(super.getDefaultAttributeModifiers(slot));

        if(slot == EquipmentSlot.MAINHAND) {

            builder.put(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            DAMAGE_UUID,
                            "energy_damage",
                            10.0D,
                            AttributeModifier.Operation.ADDITION
                    )
            );

            builder.put(
                    Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(
                            SPEED_UUID,
                            "energy_speed",
                            0.20D,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    )
            );
        }

        return builder.build();
    }
}