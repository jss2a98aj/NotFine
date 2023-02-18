package jss.notfine.gui;

import jss.notfine.core.Settings;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class GuiBackgroundManager {

    public static ResourceLocation defaultBackground = Gui.optionsBackground;

    public static ResourceLocation[] extraBackgrounds = new ResourceLocation[] {
        new ResourceLocation("textures/blocks/sand.png"),
        new ResourceLocation("textures/blocks/mycelium_top.png"),
        new ResourceLocation("textures/blocks/stonebrick.png"),
        new ResourceLocation("textures/blocks/stonebrick_mossy.png"),
        new ResourceLocation("textures/blocks/planks_oak.png"),
        new ResourceLocation("textures/blocks/planks_birch.png")
    };

    public static void setBackground() {
        int value = (int)Settings.GUI_BACKGROUND.getValue();
        if(value < 0 | value >= extraBackgrounds.length) {
            Gui.optionsBackground = defaultBackground;
        } else {
            Gui.optionsBackground = extraBackgrounds[(int)Settings.GUI_BACKGROUND.getValue()];
        }
    }

}
