
package net.lobotomy_corporation_mod.capability;

import net.lobotomy_corporation_mod.CapabilitiesInit;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MentalHealthProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final IMentalHealth instance = new MentalHealth();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CapabilitiesInit.MENTAL_HEALTH ? LazyOptional.of(() -> instance).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("mental_health", instance.getMentalHealth());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.setMentalHealth(nbt.getInt("mental_health"));
    }
}

