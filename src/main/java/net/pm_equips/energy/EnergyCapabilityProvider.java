package net.pm_equips.energy;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergyCapabilityProvider
        implements ICapabilitySerializable<CompoundTag>
{
    private final ItemStack stack;

    private final LazyOptional<IEnergyStorage> energy;

    public EnergyCapabilityProvider(
            ItemStack stack,
            int capacity,
            int receive,
            int extract)
    {
        this.stack = stack;

        CompoundTag tag = stack.getOrCreateTag();

        if (!tag.contains("Energy"))
        {
            // 初期状態は満タン
            tag.putInt("Energy", capacity);
        }

        energy = LazyOptional.of(() ->
                new ItemEnergyStorage(
                        tag,
                        capacity,
                        receive,
                        extract));
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(
            @NotNull Capability<T> cap,
            @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ENERGY)
        {
            return energy.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT()
    {
        return stack.getOrCreateTag();
    }

    @Override
    public void deserializeNBT(
            CompoundTag nbt)
    {
        stack.setTag(nbt);
    }
}