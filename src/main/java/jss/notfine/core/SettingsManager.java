package jss.notfine.core;

import jss.notfine.config.VideoSettingsConfig;
import jss.notfine.gui.GuiBackgroundManager;
import jss.notfine.render.RenderStars;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.launchwrapper.Launch;

import java.io.File;

public class SettingsManager {

    private static Minecraft mc = Minecraft.getMinecraft();

    public static VideoSettingsConfig settingsFile = new VideoSettingsConfig(
        new File(Launch.minecraftHome + File.separator + "optionsGraphics.cfg")
    );

    public static int minimumFarPlaneDistance;
    public static double cloudTranslucencyCheck;
    public static boolean shadowsFancy;
    public static boolean leavesOpaque;

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
        leavesOpaque = Settings.MODE_LEAVES.getValue() == 1 || (Settings.MODE_LEAVES.getValue() == -1 && !mc.gameSettings.fancyGraphics);
        Blocks.leaves.setGraphicsLevel(!leavesOpaque);
        Blocks.leaves2.setGraphicsLevel(!leavesOpaque);
    }

    public static void settingUpdated(Settings setting) {
        switch(setting) {
            case GUI_BACKGROUND:
                GuiBackgroundManager.setBackground();
                break;
            case CLOUD_HEIGHT:
            case MODE_CLOUD_TRANSLUCENCY:
            case MODE_CLOUDS:
            case RENDER_DISTANCE_CLOUDS:
                SettingsManager.cloudsUpdated();
                break;
            case MODE_LEAVES:
                mc.renderGlobal.loadRenderers();
                leavesUpdated();
            case MODE_SHADOWS:
                switch((int)setting.getValue()) {
                    case -1:
                        shadowsFancy = mc.gameSettings.fancyGraphics;
                        break;
                    case 0:
                        shadowsFancy = true;
                        break;
                    case 1:
                        shadowsFancy = false;
                        break;
                }
                break;
            case TOTAL_STARS:
                RenderStars.reloadStarRenderList(mc.renderGlobal);
        }
    }

}
