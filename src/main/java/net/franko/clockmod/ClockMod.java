package net.franko.clockmod;

import net.franko.clockmod.network.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ClockMod.MOD_ID)
public class ClockMod {
    public static final String MOD_ID = "clockmod";

    public ClockMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModMessages.register();
    }
}