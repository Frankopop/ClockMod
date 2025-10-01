package net.franko.clockmod.client;

import net.franko.clockmod.client.hud.ClockHudOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class ClockPacket {
    private final String text;

    public ClockPacket(String text){
        this.text = text;
    }

    public static void  encode(ClockPacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.text);
    }

    public static ClockPacket decode(FriendlyByteBuf buf) {
        return new ClockPacket(buf.readUtf(32767));
    }
    public static void handle(ClockPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            net.franko.clockmod.client.hud.ClockHudOverlay.latestText = packet.text;
        });
        context.get().setPacketHandled(true);
    }
}