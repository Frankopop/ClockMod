package net.franko.clockmod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = net.franko.clockmod.ClockMod.MOD_ID,value = Dist.CLIENT)
public class ClockHudOverlay {

    public static  String latestText = "";

    private static final int X_OFFSET_PIXELS = 10;
    private static final int Y_OFFSET_PIXELS = 10;
    private static final int COLOR = 0xFFFFAA;
    private static final float SCALE = 1.0f;

    @SubscribeEvent
    public static void  onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
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