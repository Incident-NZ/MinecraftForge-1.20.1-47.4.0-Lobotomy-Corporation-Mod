package net.lobotomy_corporation_mod.Events;

import net.lobotomy_corporation_mod.CapabilitiesInit;
import net.lobotomy_corporation_mod.items.WeaponKaliMimicry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "lobotomy_corporation_mod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        WeaponKaliMimicry.onPlayerAttacked(event);
    }
    @SubscribeEvent
    public static void onDamageDealt(LivingDamageEvent event) {
        WeaponKaliMimicry.onDamageDealt(event);
    }

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        WeaponKaliMimicry.onPlayerAttacked(event);
    }

    @SubscribeEvent
    public static void onRenderGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id() == VanillaGuiOverlay.PLAYER_HEALTH.id()) {
            event.setCanceled(true);
        }
        if (event.getOverlay().id() == VanillaGuiOverlay.FOOD_LEVEL.id()) {
            event.setCanceled(true);
        }
        if (event.getOverlay().id() == VanillaGuiOverlay.ARMOR_LEVEL.id()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderCustomHud(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay().id() == VanillaGuiOverlay.HOTBAR.id()) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                if (player.getAbilities().instabuild || player.isSpectator()) {
                    return;
                }
                renderModHud(event.getGuiGraphics(), player);
            }
        }
    }

    public static void renderModHud(GuiGraphics guiGraphics, Player player) {
        Minecraft mc = Minecraft.getInstance();
        Font font = mc.font;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int iconSize = 16;
        int padding = 6;
        int startX = screenWidth - iconSize - padding;
        int startY = screenHeight - iconSize - padding;

        // ✅ GuiGraphicsで直接描画！
        renderIconWithText(guiGraphics, font, startX, startY, "textures/gui/gui_hp", (int)player.getHealth());
        renderIconWithText(guiGraphics, font, startX - (iconSize + padding), startY, "textures/gui/gui_mp", player.getFoodData().getFoodLevel());
        renderIconWithText(guiGraphics, font, startX - 2 * (iconSize + padding), startY, "textures/gui/gui_rp", player.getAirSupply());
        renderIconWithText(guiGraphics, font, startX - 3 * (iconSize + padding), startY, "textures/gui/gui_def", player.getArmorValue());

        player.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(mentalHealth -> {
            int mentalHealthValue = mentalHealth.getMentalHealth();
            renderIconWithText(guiGraphics, font, startX - 4 * (iconSize + padding), startY, "textures/gui/gui_sp", mentalHealthValue);
        });
    }

    public static void renderIconWithText(GuiGraphics guiGraphics, Font font, int x, int y, String iconPath, int value) {

        ResourceLocation resourceLocation = new ResourceLocation("lobotomy_corporation_mod", iconPath + ".png");

        // ✅ GuiGraphicsで直接blit！
        guiGraphics.blit(resourceLocation, x, y, 0, 0, 16, 16, 16, 16);

        // 💎 中心配置
        String text = String.valueOf(value);
        int textWidth = font.width(text);
        int centerX = x + (16 - textWidth) / 2;
        int centerY = y + (16 - font.lineHeight) / 2 + 1;

        // ✨ シャドウ付き
        guiGraphics.drawString(font, text, centerX + 1, centerY + 1, 0x40000000, false);
        guiGraphics.drawString(font, text, centerX, centerY, 0xFFFFFF, false);
    }
}