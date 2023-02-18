package jss.notfine.core;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jss.notfine.gui.GuiCustomMenuButton;
import jss.notfine.gui.GuiCustomMenu;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;

import java.util.List;

public class LoadMenuButtons {

    public static final LoadMenuButtons INSTANCE = new LoadMenuButtons();

    private final Object[] settings = new Object[] {
        GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE,
        GameSettings.Options.FRAMERATE_LIMIT, null,
        GameSettings.Options.ENABLE_VSYNC, GameSettings.Options.USE_FULLSCREEN,
        GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.GAMMA,
        GameSettings.Options.GUI_SCALE, GameSettings.Options.VIEW_BOBBING,
        GameSettings.Options.PARTICLES, Settings.PARTICLES_ENC_TABLE,
        Settings.PARTICLES_VOID, Settings.MODE_LEAVES,
        Settings.MODE_SKY, Settings.MODE_CLOUDS,
        Settings.RENDER_DISTANCE_CLOUDS, Settings.CLOUD_HEIGHT,
        Settings.CLOUD_SCALE, Settings.MODE_CLOUD_TRANSLUCENCY,
        Settings.MODE_GLINT_WORLD, Settings.MODE_GLINT_INV,
        Settings.TOTAL_STARS, null,
        Settings.MODE_SHADOWS, GameSettings.Options.ANAGLYPH,
        GameSettings.Options.ANISOTROPIC_FILTERING, GameSettings.Options.MIPMAP_LEVELS
    };

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGui(InitGuiEvent.Post event) {
        if(event.gui instanceof GuiOptions) {
            GuiButton videoSettings = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 101).findFirst().get();
            videoSettings.visible = false;

            event.buttonList.add(
                new GuiCustomMenuButton(
                    -1,
                    videoSettings.xPosition, videoSettings.yPosition,
                    videoSettings.width, videoSettings.height,
                    "NotFine Alpha",
                    new GuiCustomMenu(event.gui, "NotFine Alpha Settings", settings)
                )
            );
        }
    }

}
