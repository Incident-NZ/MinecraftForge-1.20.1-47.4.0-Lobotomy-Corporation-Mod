package net.lobotomy_corporation_mod.Events;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.lobotomy_corporation_mod.client.ClientForgeEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(modid = Lobotomy_corporation_mod.MOD_ID, value = Dist.CLIENT)
public class OverlayHandler {
    private static final ResourceLocation SCOPE_OVERLAY =
            new ResourceLocation(Lobotomy_corporation_mod.MOD_ID, "textures/gui/gun_scope_overlay.png");

    // 🔹オーバーレイ対象アイテム
    private static final List<RegistryObject<Item>> OVERLAY_ITEMS = List.of(
            ItemInit.W2_FOURTH_MATCH_FIRE,
            ItemInit.W2_BEAK,
            ItemInit.W3_LAETITIA,
            ItemInit.W3_HARMONY,
            ItemInit.W4_MAGIC_BULLET,
            ItemInit.W4_SOLEMN_LAMENT_L,
            ItemInit.W4_HORNET
    );

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) return;

        // 🔹対象のアイテムかチェック
        if (!isScopeItem(stack.getItem())) return;

        // 🔹キー押下チェック
        if (!ClientForgeEvents.isScopeActive) return;

        // HUDにスコープ描画
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // 🔹中央寄せ用サイズと位置
        int overlayWidth = 256;  // 描画するテクスチャの幅
        int overlayHeight = 256; // 描画するテクスチャの高さ
        int x = (screenWidth - overlayWidth) / 2;
        int y = (screenHeight - overlayHeight) / 2;

        event.getGuiGraphics().blit(
                SCOPE_OVERLAY,
                x, y,               // 描画開始位置を中央に
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
}


