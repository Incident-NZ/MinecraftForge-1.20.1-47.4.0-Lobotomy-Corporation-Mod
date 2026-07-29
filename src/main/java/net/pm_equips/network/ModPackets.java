package net.pm_equips.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.pm_equips.items.EGOW4MagicBullet;
import net.pm_equips.items.WeaponRolandLogicHG;
import net.pm_equips.items.WeaponRolandLogicSG;

import java.util.UUID;
import java.util.function.Supplier;

public class ModPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new net.minecraft.resources.ResourceLocation("pm_equips", "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        INSTANCE.registerMessage(0, ActivateAbilityPacket.class,
                ActivateAbilityPacket::encode, ActivateAbilityPacket::decode, ActivateAbilityPacket::handle);

        INSTANCE.registerMessage(1, ReloadPacket.class,
                ReloadPacket::encode, ReloadPacket::decode, ReloadPacket::handle);
    }

    public static class ActivateAbilityPacket {
        private final UUID targetUUID;

        public ActivateAbilityPacket(UUID targetUUID) {
            this.targetUUID = targetUUID;
        }

        public static void encode(ActivateAbilityPacket msg, FriendlyByteBuf buf) {
            buf.writeUUID(msg.targetUUID);
        }

        public static ActivateAbilityPacket decode(FriendlyByteBuf buf) {
            return new ActivateAbilityPacket(buf.readUUID());
        }

        public static void handle(ActivateAbilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                net.minecraft.server.level.ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    net.minecraft.world.item.ItemStack gun = player.getMainHandItem();
                    if (gun.getItem() instanceof EGOW4MagicBullet && gun.getOrCreateTag().getInt("abilityCharges") > 0) {
                        gun.getOrCreateTag().putBoolean("abilityActive", true);
                        gun.getOrCreateTag().putUUID("targetUUID", msg.targetUUID);
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }

    public static class ReloadPacket {
        public ReloadPacket() {}

        public static void encode(ReloadPacket msg, FriendlyByteBuf buf) {}

        public static ReloadPacket decode(FriendlyByteBuf buf) { return new ReloadPacket(); }

        public static void handle(ReloadPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                net.minecraft.server.level.ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    tryStartReload(player, InteractionHand.MAIN_HAND, player.getMainHandItem());
                    tryStartReload(player, InteractionHand.OFF_HAND, player.getOffhandItem());
                }
            });
            ctx.get().setPacketHandled(true);
        }

        private static void tryStartReload(net.minecraft.server.level.ServerPlayer player, InteractionHand hand, ItemStack stack) {
            if (stack.isEmpty()) {
                return;
            }

            if (stack.getItem() instanceof WeaponRolandLogicHG) {
                ((WeaponRolandLogicHG) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof WeaponRolandLogicSG) {
                ((WeaponRolandLogicSG) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW3Laetitia) {
                ((net.pm_equips.items.EGOW3Laetitia) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW2Beak) {
                ((net.pm_equips.items.EGOW2Beak) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW2Match) {
                ((net.pm_equips.items.EGOW2Match) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW3Harmony) {
                ((net.pm_equips.items.EGOW3Harmony) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW4LamentR) {
                ((net.pm_equips.items.EGOW4LamentR) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW4Hornet) {
                ((net.pm_equips.items.EGOW4Hornet) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW4CrimsonScarL) {
                ((net.pm_equips.items.EGOW4CrimsonScarL) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.EGOW4MagicBullet) {
                ((net.pm_equips.items.EGOW4MagicBullet) stack.getItem()).startReload(stack, player);
            } else if (stack.getItem() instanceof net.pm_equips.items.RCorpRabbitRifle) {
                ((net.pm_equips.items.RCorpRabbitRifle) stack.getItem()).startReload(stack, player);
            }
        }
    }
}
