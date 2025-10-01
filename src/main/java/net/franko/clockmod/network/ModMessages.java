package net.franko.clockmod.network;

import net.franko.clockmod.ClockMod;
import net.franko.clockmod.client.ClockPacket;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class ModMessages {
    private  static final String PROTOCOL_VERSION = "1";
    public static  final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new net.minecraft.resources.ResourceLocation(ClockMod.MOD_ID, "messages"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetID = 0;

    public  static void register(){
        CHANNEL.registerMessage(packetID++, ClockPacket.class,
                ClockPacket::encode,
                ClockPacket::decode,
                ClockPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
