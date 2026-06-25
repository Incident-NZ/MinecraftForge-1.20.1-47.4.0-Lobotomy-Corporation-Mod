package net.pm_equips.energy;

import net.minecraftforge.energy.EnergyStorage;

public class WcorpWeaponStorage extends EnergyStorage {

    public WcorpWeaponStorage() {
        super(100000, 1000);
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    public boolean canExtract() {
        return true;
    }
}
