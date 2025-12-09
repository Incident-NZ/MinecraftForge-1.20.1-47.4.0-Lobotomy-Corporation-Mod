package net.lobotomy_corporation_mod.client;

import net.lobotomy_corporation_mod.EntityInit;
import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.lobotomy_corporation_mod.client.renderer.BulletRenderer;
import net.lobotomy_corporation_mod.client.renderer.MagicBulletRenderer;
import net.lobotomy_corporation_mod.client.renderer.m5r_t0346;
import net.lobotomy_corporation_mod.items.W4MagicBullet;
import net.lobotomy_corporation_mod.network.ModPackets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static net.lobotomy_corporation_mod.client.ClientKeyBindings.EGO_ABILITY_KEY;

@Mod.EventBusSubscriber(
        modid = Lobotomy_corporation_mod.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.W5_SOUND_OF_A_STAR_PROJECTILE.get(),
                ctx -> new ThrownItemRenderer<>(ctx, 1.0f, true));
        event.registerEntityRenderer(EntityInit.MAGIC_BULLET.get(),
                MagicBulletRenderer::new);
        event.registerEntityRenderer(EntityInit.BULLET.get(),
                BulletRenderer::new);
        event.registerEntityRenderer(EntityInit.M5_T0346.get(), m5r_t0346::new);
    }

    @Mod.EventBusSubscriber(modid = "lobotomy_corporation_mod", value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft mc = Minecraft.getInstance();
                Player player = mc.player;
                if (player != null && EGO_ABILITY_KEY.consumeClick()) {
                    ItemStack gun = player.getMainHandItem();
                    if (gun.getItem() instanceof W4MagicBullet && gun.getOrCreateTag().getInt("abilityCharges") > 0) {
                        // 照準エンティティ取得
                        HitResult hit = mc.hitResult;
                        if (hit instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity target) {
                            UUID targetUUID = target.getUUID();
                            ModPackets.INSTANCE.sendToServer(new ModPackets.ActivateAbilityPacket(targetUUID));
                        }
                    }
                }
            }
        }
    }
}

