package net.pm_equips;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, PMEquipsMain.MOD_ID);

    public static final RegistryObject<SoundEvent> EGO_LAMENT =
            register("item.ego_lament");
    public static final RegistryObject<SoundEvent> EGO_MAGIC_BULLET =
            register("item.ego_magic_bullet");
    public static final RegistryObject<SoundEvent> GUN_AUTO =
            register("item.gun_auto");
    public static final RegistryObject<SoundEvent> GUN_SEMI =
            register("item.gun_semi");
    public static final RegistryObject<SoundEvent> GUN_ROLAND_REVOLVER =
            register("item.gun_roland_revolver");
    public static final RegistryObject<SoundEvent> GUN_ROLAND_SHOTGUN =
            register("item.gun_roland_shotgun");

    private static RegistryObject<SoundEvent> register(String name)
    {
        return SOUNDS.register(name,
                () -> SoundEvent.createVariableRangeEvent(
                        new ResourceLocation(PMEquipsMain.MOD_ID, name)
                ));
    }
}
