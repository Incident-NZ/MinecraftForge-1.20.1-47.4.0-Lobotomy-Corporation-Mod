package net.pm_equips;

import net.pm_equips.config.Config;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(PMEquipsMain.MOD_ID)
public class PMEquipsMain {

    public static final String MOD_ID = "pm_equips";

    public PMEquipsMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockInit.Blocks.BLOCKS.register(modEventBus);
        BlockInit.BlockItems.BLOCK_ITEMS.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        TabInit.CREATIVE_TABS.register(modEventBus);
        EntityInit.ENTITY_TYPES.register(modEventBus);
        MobEffectInit.MOB_EFFECTS.register(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);

        modEventBus.addListener(this::setup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
                Config.CLIENT_CONFIG, MOD_ID + "-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        GeckoLib.initialize();
        // register network packets
        net.pm_equips.network.ModPackets.register();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading event) {}

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading event) {}
}
