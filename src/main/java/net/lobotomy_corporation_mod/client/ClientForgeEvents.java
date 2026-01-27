package net.lobotomy_corporation_mod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lobotomy_corporation_mod.CapabilitiesInit;
import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.capability.MentalHealthProvider;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.lobotomy_corporation_mod.lobotomy_corporation_mod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(
        modid = lobotomy_corporation_mod.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT)
public class ClientForgeEvents {

    public static boolean isScopeActive = false;

    //Overlay
    private static final ResourceLocation SCOPE_OVERLAY =
            new ResourceLocation(lobotomy_corporation_mod.MOD_ID, "textures/gui/gun_scope_overlay.png");

    private static final List<RegistryObject<Item>> OVERLAY_ITEMS = List.of(
            ItemInit.W2_FOURTH_MATCH_FIRE,
            ItemInit.W2_BEAK,
            ItemInit.W3_LAETITIA,
            ItemInit.W3_HARMONY,
            ItemInit.W4_MAGIC_BULLET,
            ItemInit.W4_SOLEMN_LAMENT_R,
            ItemInit.W4_HORNET,
            ItemInit.W5_PARADISE_LOST
    );

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) return;
        if (!isScopeItem(stack.getItem())) return;
        if (!net.lobotomy_corporation_mod.client.ClientForgeEvents.isScopeActive) return;

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int overlayWidth = 256;
        int overlayHeight = 256;
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
            isScopeActive = ClientKeyBindings.SCOPE_KEY.isDown();
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
            event.setFOV(45.0F);
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

        // ホットバー中央（スロット5）を基準に横中央揃えで配置
        int iconsCount = 5; // HP, 空腹, 防具, 精神, 水中ゲージ
        int totalWidth = iconsCount * iconSize + (iconsCount - 1) * padding;
        int startX = screenWidth / 2 - totalWidth / 2;

        // ホットバーの上に表示するY位置（微調整可能）
        int hotbarHeight = 22; // 目安
        int hotbarOffset = 4;
        int startY = screenHeight - hotbarHeight - iconSize - padding - hotbarOffset;

        // 左から: HP, 空腹度, 防具値, 精神力, 水中ゲージ
        int x = startX;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_hp", (int) player.getHealth());
        x += iconSize + padding;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_mp", player.getFoodData().getFoodLevel());
        x += iconSize + padding;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_def", player.getArmorValue());
        x += iconSize + padding;
        int finalX = x;
        player.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(mentalHealth -> {
            int mentalHealthValue = mentalHealth.getMentalHealth();
            renderIconWithText(guiGraphics, font, finalX, startY, "textures/gui/gui_sp", mentalHealthValue);
        });
        x += iconSize + padding;
        renderIconWithText(guiGraphics, font, x, startY, "textures/gui/gui_rp", player.getAirSupply());
    }

    public static void renderIconWithText(GuiGraphics guiGraphics, Font font, int x, int y, String iconPath, int value) {
        ResourceLocation resourceLocation = new ResourceLocation("lobotomy_corporation_mod", iconPath + ".png");
        guiGraphics.blit(resourceLocation, x, y, 0, 0, 16, 16, 16, 16);

        String text = String.valueOf(value);
        int textWidth = font.width(text);
        int centerX = x + (16 - textWidth) / 2;
        int centerY = y + (16 - font.lineHeight) / 2 + 1;

        guiGraphics.drawString(font, text, centerX + 1, centerY + 1, 0x40000000, false);
        guiGraphics.drawString(font, text, centerX, centerY, 0xFFFFFF, false);
    }

    //Capability
    @SubscribeEvent
    public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation("lobotomy_corporation_mod", "mental_health"),
                    new MentalHealthProvider());
        }
    }

    @SubscribeEvent
    public static void onMobKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.getCapability(CapabilitiesInit.MENTAL_HEALTH).ifPresent(w -> w.addMentalHealth(10));
        }
    }
}
