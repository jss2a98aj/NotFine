package jss.notfine.core;

import jss.notfine.config.VideoSettingsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.Blocks;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;

import java.io.File;

public class SettingsManager {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static VideoSettingsConfig settingsFile = new VideoSettingsConfig(
        new File(Launch.minecraftHome + File.separator + "optionsGraphics.cfg")
    );

    public static int minimumFarPlaneDistance;
    public static double cloudTranslucencyCheck;
    public static boolean shadows;
    public static boolean leavesOpaque;
    public static boolean vignette;

    public static ResourceLocation defaultBackground = Gui.optionsBackground;
    public static ResourceLocation[] extraBackgrounds = new ResourceLocation[] {
        new ResourceLocation("textures/blocks/sand.png"),
        new ResourceLocation("textures/blocks/mycelium_top.png"),
        new ResourceLocation("textures/blocks/stonebrick.png"),
        new ResourceLocation("textures/blocks/stonebrick_mossy.png"),
        new ResourceLocation("textures/blocks/planks_oak.png"),
        new ResourceLocation("textures/blocks/planks_birch.png")
    };

    public static void backgroundUpdated() {
        int value = (int)Settings.GUI_BACKGROUND.getValue();
        if(value < 0 | value >= extraBackgrounds.length) {
            Gui.optionsBackground = defaultBackground;
        } else {
            Gui.optionsBackground = extraBackgrounds[(int)Settings.GUI_BACKGROUND.getValue()];
        }
    }

    public static void cloudsUpdated() {
        if(Settings.MODE_CLOUDS.getValue() != 2f) {
            minimumFarPlaneDistance = (int)(32f * Settings.RENDER_DISTANCE_CLOUDS.getValue());
            minimumFarPlaneDistance += Math.abs(Settings.CLOUD_HEIGHT.getValue());
            mc.gameSettings.clouds = true;
        } else {
            minimumFarPlaneDistance = 128;
            mc.gameSettings.clouds = false;
        }
        switch((int) Settings.MODE_CLOUD_TRANSLUCENCY.getValue()) {
            case -1:
                cloudTranslucencyCheck = Settings.CLOUD_HEIGHT.getValue();
                break;
            case 0:
                cloudTranslucencyCheck = Double.NEGATIVE_INFINITY;
                break;
            case 1:
                cloudTranslucencyCheck = Double.POSITIVE_INFINITY;
                break;
        }
    }

    public static void leavesUpdated() {
        mc.renderGlobal.loadRenderers();
        leavesOpaque = Settings.MODE_LEAVES.getValue() == 1 || (Settings.MODE_LEAVES.getValue() == -1 && !mc.gameSettings.fancyGraphics);
        Blocks.leaves.setGraphicsLevel(!leavesOpaque);
        Blocks.leaves2.setGraphicsLevel(!leavesOpaque);
    }

    public static void shadowsUpdated() {
        switch((int)Settings.MODE_SHADOWS.getValue()) {
            case -1:
                shadows = mc.gameSettings.fancyGraphics;
                break;
            case 0:
                shadows = true;
                break;
            case 1:
                shadows = false;
                break;
        }
    }

    public static void vignetteUpdated() {
        switch((int)Settings.MODE_VIGNETTE.getValue()) {
            case -1:
                vignette = mc.gameSettings.fancyGraphics;
                break;
            case 0:
                vignette = true;
                break;
            case 1:
                vignette = false;
                break;
        }
    }

    public static void graphicsUpdated() {
        leavesUpdated();
        shadowsUpdated();
        vignetteUpdated();
    }

}
