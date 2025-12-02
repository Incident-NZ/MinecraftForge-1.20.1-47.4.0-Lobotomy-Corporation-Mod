package net.lobotomy_corporation_mod.network;

import net.lobotomy_corporation_mod.items.W4MagicBullet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.UUID;
import java.util.function.Supplier;

public class ModPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new net.minecraft.resources.ResourceLocation("lobotomy_corporation_mod", "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        INSTANCE.registerMessage(0, ActivateAbilityPacket.class,
                ActivateAbilityPacket::encode, ActivateAbilityPacket::decode, ActivateAbilityPacket::handle);
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
                    if (gun.getItem() instanceof W4MagicBullet && gun.getOrCreateTag().getInt("abilityCharges") > 0) {
                        gun.getOrCreateTag().putBoolean("abilityActive", true);
                        gun.getOrCreateTag().putUUID("targetUUID", msg.targetUUID);
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}