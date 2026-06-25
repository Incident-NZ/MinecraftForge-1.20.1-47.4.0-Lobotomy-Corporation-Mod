package net.pm_equips.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.energy.EnergyStorage;

public class ItemEnergyStorage extends EnergyStorage {

    public ItemEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public void setEnergy(int energy) {
        this.energy = Math.min(capacity, energy);
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Energy", energy);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        energy = tag.getInt("Energy");
    }
}
