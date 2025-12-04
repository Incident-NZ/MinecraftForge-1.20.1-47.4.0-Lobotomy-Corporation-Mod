package net.lobotomy_corporation_mod;

import net.lobotomy_corporation_mod.config.Config;
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

@Mod(Lobotomy_corporation_mod.MOD_ID)
public class Lobotomy_corporation_mod {

    public static final String MOD_ID = "lobotomy_corporation_mod";

    public Lobotomy_corporation_mod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        BlockInit.Blocks.BLOCKS.register(modEventBus);
        BlockInit.BlockItems.BLOCK_ITEMS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        TabInit.CREATIVE_TABS.register(modEventBus);
        EntityInit.ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::setup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,
                Config.CLIENT_CONFIG, MOD_ID + "-common.toml");

        // ネットワークイベント・キー入力・ズームなどのカスタムイベントを登録したい場合は以下でOK
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // パケット登録を初期化段階で呼び出す（enqueueWorkを使わず即実行）
        GeckoLib.initialize();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading event) {}

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading event) {}
}
