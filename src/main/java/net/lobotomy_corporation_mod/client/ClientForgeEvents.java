package net.lobotomy_corporation_mod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lobotomy_corporation_mod.CapabilitiesInit;
import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.capability.MentalHealthProvider;
import net.lobotomy_corporation_mod.items.W5Justitia;
import net.lobotomy_corporation_mod.items.W5Mimicry;
import net.lobotomy_corporation_mod.items.WeaponKaliMimicry;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.lobotomy_corporation_mod.lobotomy_corporation_mod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
        int startX = screenWidth - iconSize - padding;
        int startY = screenHeight - iconSize - padding;

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

    //EGOPassiveSkill
    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack stack = player.getMainHandItem();

        if (stack.getItem() instanceof W5Justitia &&
                player.isUsingItem() &&
                !player.getCooldowns().isOnCooldown(stack.getItem())) {

            event.setCanceled(true);

            player.level().playSound(null, player.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(stack.getItem(), 20 * 10);
            player.stopUsingItem();
        }

        if (stack.getItem() instanceof W5Mimicry &&
                player.isUsingItem() &&
                !player.getCooldowns().isOnCooldown(stack.getItem())) {

            event.setCanceled(true);
            player.level().playSound(null, player.blockPosition(), SoundEvents.ANVIL_FALL, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(stack.getItem(), 20 * 15); // 15秒
            player.stopUsingItem();
        }

        WeaponKaliMimicry.onPlayerAttacked(event);
    }

    private static final String ATTACK_COUNT_TAG = "mimicry_attack_count";
    private static final String DAMAGE_ACCUM_TAG = "mimicry_damage_total";

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        DamageSource source = event.getSource();

        if (!(source.getEntity() instanceof Player player)) return;

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof W5Mimicry)) {
            stack = player.getOffhandItem();
            if (!(stack.getItem() instanceof W5Mimicry)) return;
        }

        var tag = stack.getOrCreateTag();
        int count = tag.getInt(ATTACK_COUNT_TAG);
        float accum = tag.getFloat(DAMAGE_ACCUM_TAG);

        count++;
        accum += event.getAmount();

        if (count >= 4) {
            float heal = accum * 0.25f;
            player.heal(heal);
            count = 0;
            accum = 0;

            Level level = player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        tag.putInt(ATTACK_COUNT_TAG, count);
        tag.putFloat(DAMAGE_ACCUM_TAG, accum);
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        W5Justitia.onLivingDeath(event);
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        WeaponKaliMimicry.onPlayerAttacked(event);
    }

    @SubscribeEvent
    public static void onDamageDealt(LivingDamageEvent event) {
        WeaponKaliMimicry.onDamageDealt(event);
    }


}
