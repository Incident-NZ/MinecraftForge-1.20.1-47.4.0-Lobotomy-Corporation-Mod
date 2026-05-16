package net.pm_equips.client;

import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.pm_equips.EntityInit;
import net.pm_equips.KeyBindInit;
import net.pm_equips.client.renderer.*;
import net.pm_equips.PMEquipsMain;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = PMEquipsMain.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public class ClientEvents {

    //EntityRenderer
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.W5_SOUND_OF_A_STAR_PROJECTILE.get(),
                ctx -> new ThrownItemRenderer<>(ctx, 1.0f, true));
        event.registerEntityRenderer(EntityInit.MAGIC_BULLET.get(),
                AmmoMagicR::new);
        event.registerEntityRenderer(EntityInit.BULLET_LARV.get(),
                AmmoLARVR::new);
        event.registerEntityRenderer(EntityInit.BULLET_LASG.get(),
                AmmoLASGR::new);
        event.registerEntityRenderer(EntityInit.BULLET.get(),
                AmmoR::new);
        event.registerEntityRenderer(EntityInit.BULLET_EX.get(),
                AmmoExR::new);
        // BlockEntity renderer for EBoxGen
        event.registerBlockEntityRenderer(net.pm_equips.BlockEntityInit.EBOX_GEN.get(), ctx -> new net.pm_equips.client.renderer.EBoxGenR());
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(KeyBindInit.RELOAD_KEY);
        event.register(KeyBindInit.SCOPE_KEY);
    }
}

