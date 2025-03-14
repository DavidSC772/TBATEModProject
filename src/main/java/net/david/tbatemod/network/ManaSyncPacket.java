package net.david.tbatemod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class ManaSyncPacket {
    private final int mana;
    private final int maxMana;

    public ManaSyncPacket(int mana, int maxMana) {
        this.mana = mana;
        this.maxMana = maxMana;
    }

    public static void encode(ManaSyncPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.mana);
        buffer.writeInt(packet.maxMana);
    }

    public static ManaSyncPacket decode(FriendlyByteBuf buffer) {
        int mana = buffer.readInt();
        int maxMana = buffer.readInt();
        return new ManaSyncPacket(mana, maxMana);
    }

    public static void handle(ManaSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            var player = contextSupplier.get().getSender();
            if (player != null) {
                player.getCapability(net.david.tbatemod.manasystem.manabar.ManaStorage.MANA).ifPresent(mana -> {
                    mana.setMana(packet.mana);
                    mana.setMaxMana(packet.maxMana);
                });
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
