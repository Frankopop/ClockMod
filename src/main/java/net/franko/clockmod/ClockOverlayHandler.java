package net.franko.clockmod;

import net.franko.clockmod.client.ClockPacket;
import net.franko.clockmod.network.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ClockMod.MOD_ID)
public class ClockOverlayHandler {

    private static  final String[] GIORNI_SETTIMANA = {
            "Lunedì", "Martedì", "Mercoledì", "Giovedì",
            "Venerdì", "Sabato", "Domenica"
    };

    private static final int TICKS_PER_SECOND = 20;
    private static int serverTickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (++serverTickCounter <TICKS_PER_SECOND) return;
        serverTickCounter = 0;

        ServerLevel world = event.getServer().overworld();
        if (world == null) return;

        long totalDays = world.getDayTime() / 24000;
        long timeOfDay = world.getDayTime() % 24000;

        int hour = (int) ((timeOfDay / 1000 + 6) % 24);
        int minute = (int) ((timeOfDay % 1000) * 60 / 1000);
        String weekday = GIORNI_SETTIMANA[(int)(totalDays % 7)];

        int year = 0, month = 1, day = 1;
        long days = totalDays;
        while (days > 0) {
            int mday = switch (month) {
                case 2 -> isLseapYear(year) ? 29 : 28;
                case 4,6,9,11 -> 30;
                default -> 31;
            };
            if (day < mday) day++;
            else { day=1; if(month<12) month++; else { month=1; year++; } }
            days --;
        }

        String formatted = String.format("%02d:%02d - %s - %02d/%02d/&04d",
                hour,month, weekday, day, month, year);

        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            ModMessages.CHANNEL.sendTo(new ClockPacket(formatted),
                    player.connection.connection, net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT);
        }
    }
    private  static  boolean isLseapYear(int y) {
        return (y%4==0 && y%100!=0) || (y%400==0);
    }
}