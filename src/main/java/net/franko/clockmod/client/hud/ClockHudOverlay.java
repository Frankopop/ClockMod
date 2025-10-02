package net.franko.clockmod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = net.franko.clockmod.ClockMod.MOD_ID, value = Dist.CLIENT)
public class ClockHudOverlay {

    public static String latestText = "";

    // <<< static boolean dichiarata qui, a livello di classe
    private static boolean hudVisible = true;

    private static final int X_OFFSET_PIXELS = 10;
    private static final int Y_OFFSET_PIXELS = 10;
    private static final int COLOR = 0xFFFFAA;
    private static final float SCALE = 1.0f;

    // metodi pubblici static per controllare l'HUD
    public static void setHudVisible(boolean visible) {
        hudVisible = visible;
    }

    public static boolean isHudVisible() {
        return hudVisible;
    }

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        if (!hudVisible) return; // <- controllo visibilità
        PoseStack poseStack = event.getPoseStack();
        Minecraft mc = Minecraft.getInstance();

        RenderSystem.enableBlend();
        poseStack.pushPose();
        poseStack.scale(SCALE, SCALE, 1.0f);

        float x = X_OFFSET_PIXELS / SCALE;
        float y = (event.getWindow().getGuiScaledHeight() - Y_OFFSET_PIXELS - mc.font.lineHeight * SCALE) / SCALE;

        mc.font.draw(poseStack, latestText, x, y, COLOR);

        poseStack.popPose();
        RenderSystem.disableBlend();
    }
}
