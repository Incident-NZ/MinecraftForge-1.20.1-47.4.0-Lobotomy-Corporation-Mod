package net.lobotomy_corporation_mod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientKeyBindings {
    public static KeyMapping SCOPE_KEY;
    public static KeyMapping EGO_ABILITY_KEY;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        SCOPE_KEY = new KeyMapping(
                "key.lobotomy_corporation.scope", // 翻訳キー
                InputConstants.KEY_LSHIFT,       // デフォルト LSHIFT
                "key.categories.lobotomy_corporation_mod" // カテゴリ
        );
        event.register(SCOPE_KEY);

        EGO_ABILITY_KEY = new KeyMapping(
                "key.lobotomy_corporation_mod.special_skill",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V, // デフォルト V
                "key.categories.lobotomy_corporation_mod"
        );
        event.register(EGO_ABILITY_KEY);
    }
}
