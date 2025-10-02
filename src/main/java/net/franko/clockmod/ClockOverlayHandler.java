package net.franko.clockmod;

import net.franko.clockmod.client.ClockPacket;
import net.franko.clockmod.network.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Mod.EventBusSubscriber(modid = ClockMod.MOD_ID)
public class ClockOverlayHandler {

    private static final int TICKS_PER_SECOND = 20;
    private static int serverTickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (++serverTickCounter < TICKS_PER_SECOND) return;
        serverTickCounter = 0;

        ServerLevel world = event.getServer().overworld();
        if (world == null) return;

        long totalDays = world.getDayTime() / 24000;
        long timeOfDay = world.getDayTime() % 24000;

        int hour = (int) ((timeOfDay / 1000 + 6) % 24);
        int minute = (int) ((timeOfDay % 1000) * 60 / 1000);

        // Data base: Minecraft Day 0 = 1/1/0
        LocalDate baseDate = LocalDate.of(0, 1, 1);
        LocalDate currentDate = baseDate.plus(totalDays, ChronoUnit.DAYS);

        DayOfWeek weekday = currentDate.getDayOfWeek();
        String weekdayName = weekday.getDisplayName(TextStyle.FULL, Locale.ITALIAN);

        String formatted = String.format("%02d:%02d - %s - %02d/%02d/%04d",
                hour, minute,
                weekdayName,
                currentDate.getDayOfMonth(),
                currentDate.getMonthValue(),
                currentDate.getYear());

        // Invia il pacchetto a tutti i giocatori
        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            ModMessages.CHANNEL.sendTo(
                    new ClockPacket(formatted),
                    player.connection.connection,
                    net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT
            );
        }
    }
}
