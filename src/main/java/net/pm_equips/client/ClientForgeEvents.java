package net.pm_equips.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraftforge.fml.common.Mod;
import net.pm_equips.ItemInit;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraft.resources.ResourceLocation;
import net.pm_equips.PMEquipsMain;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.pm_equips.KeyBindInit;
import net.pm_equips.network.ModPackets;

import java.util.List;

@Mod.EventBusSubscriber(
        modid = PMEquipsMain.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT)
public class ClientForgeEvents {

    public static boolean isScopeActive = false;

    //Overlay
    private static final ResourceLocation SCOPE_OVERLAY =
            new ResourceLocation(PMEquipsMain.MOD_ID, "textures/gui/gun_scope.png");

    private static final List<RegistryObject<Item>> OVERLAY_ITEMS = List.of(
            ItemInit.W2_FOURTH_MATCH_FIRE,
            ItemInit.W2_BEAK,
            ItemInit.W3_LAETITIA,
            ItemInit.W3_HARMONY,
            ItemInit.W4_MAGIC_BULLET,
            ItemInit.W4_SOLEMN_LAMENT_R,
            ItemInit.W4_HORNET,
            ItemInit.W5_PARADISE_LOST,
            ItemInit.WEAPON_ROLAND_REVOLVER,
            ItemInit.WEAPON_ROLAND_SHOTGUN
    );

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) return;
        if (!isScopeItem(stack.getItem())) return;
        if (!net.pm_equips.client.ClientForgeEvents.isScopeActive) return;

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int overlayWidth = 64;
        int overlayHeight = 64;
        int x = (screenWidth - overlayWidth) / 2;
        int y = (screenHeight - overlayHeight) / 2;

        event.getGuiGraphics().blit(
                SCOPE_OVERLAY,
                x, y,
                0, 0,
                overlayWidth, overlayHeight,
                overlayWidth, overlayHeight
        );

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    public static boolean isScopeItem(Item item) {
        for (RegistryObject<Item> regObj : OVERLAY_ITEMS) {
            if (regObj.get() == item) return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            isScopeActive = KeyBindInit.SCOPE_KEY.isDown();
            if (KeyBindInit.RELOAD_KEY.consumeClick()) {
                ModPackets.INSTANCE.sendToServer(new ModPackets.ReloadPacket());
            }
        }
    }

    @SubscribeEvent
    public static void onFovModifier(ViewportEvent.ComputeFov event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) return;

        if (ClientForgeEvents.isScopeItem(stack.getItem()) && isScopeActive) {
            event.setFOV(event.getFOV() * 0.5F);
        }
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

        // ホットバー左端に揃える（バニラのホットバー中央基準から左へ 91px）
        int startX = screenWidth / 2 - 91;

        // ホットバーの上に表示するY位置（微調整可能）
        int hotbarHeight = 22; // 目安
        int hotbarOffset = 4;
        int startY = screenHeight - hotbarHeight - iconSize - padding - hotbarOffset;

        // 左から: HP, 空腹度, 防具
        int x = startX;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_hp", (int) player.getHealth());
        x += iconSize + padding;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_mp", player.getFoodData().getFoodLevel());
        x += iconSize + padding;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_def", player.getArmorValue());
        // 精神力は非表示、酸素ゲージはバニラのまま
    }

    public static void renderIconWithText(GuiGraphics guiGraphics, Font font, int x, int y, String iconPath, int value) {
        ResourceLocation resourceLocation = new ResourceLocation("pm_equips", iconPath + ".png");
        guiGraphics.blit(resourceLocation, x, y, 0, 0, 16, 16, 16, 16);

        String text = String.valueOf(value);
        int textWidth = font.width(text);
        int centerX = x + (16 - textWidth) / 2;
        int centerY = y + (16 - font.lineHeight) / 2 + 1;

        guiGraphics.drawString(font, text, centerX + 1, centerY + 1, 0x40000000, false);
        guiGraphics.drawString(font, text, centerX, centerY, 0xFFFFFF, false);
    }
}
