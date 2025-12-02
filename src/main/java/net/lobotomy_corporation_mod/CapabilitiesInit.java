package net.lobotomy_corporation_mod;

import net.lobotomy_corporation_mod.capability.IMentalHealth;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CapabilitiesInit {
    public static final Capability<IMentalHealth> MENTAL_HEALTH =
            CapabilityManager.get(new CapabilityToken<>() {});
}