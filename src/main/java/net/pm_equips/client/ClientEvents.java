package net.pm_equips.client;

import net.pm_equips.EntityInit;
import net.pm_equips.client.renderer.BulletExRenderer;
import net.pm_equips.PMEquipsMain;
import net.pm_equips.client.renderer.BulletRenderer;
import net.pm_equips.client.renderer.BulletMRenderer;
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
                BulletMRenderer::new);
        event.registerEntityRenderer(EntityInit.BULLET.get(),
                BulletRenderer::new);
        event.registerEntityRenderer(EntityInit.BULLET_EX.get(),
                BulletExRenderer::new);
    }
}

