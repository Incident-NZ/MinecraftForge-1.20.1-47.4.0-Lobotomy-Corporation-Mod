package net.pm_equips.energy;


import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WcorpWeaponProvider
        implements ICapabilitySerializable<CompoundTag> {

    private final ItemEnergyStorage storage =
            new ItemEnergyStorage(100000, 1000);

    private final LazyOptional<IEnergyStorage> optional =
            LazyOptional.of(() -> storage);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(
            @NotNull Capability<T> cap,
            @Nullable Direction side) {

        return cap == ForgeCapabilities.ENERGY
                ? optional.cast()
                : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return storage.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        storage.deserializeNBT(nbt);
    }
}