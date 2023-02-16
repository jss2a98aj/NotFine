package jss.notfine.core;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jss.notfine.gui.GuiCustomMenuButton;
import jss.notfine.gui.GuiNotFineSettings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;

public class LoadMenuButtons {

    public static final LoadMenuButtons INSTANCE = new LoadMenuButtons();

    private final Object[] settings = new Object[] {
        Settings.MODE_SKY, Settings.MODE_LEAVES,
        Settings.MODE_CLOUDS, Settings.RENDER_DISTANCE_CLOUDS,
        Settings.CLOUD_SCALE, Settings.CLOUD_HEIGHT,
        Settings.MODE_CLOUD_TRANSLUCENCY, Settings.PARTICLES_ENC_TABLE,
        Settings.MODE_GLEAM_WORLD, Settings.MODE_GLEAM_INV,
        Settings.TOTAL_STARS, null,
        Settings.PARTICLES_VOID, Settings.MODE_SHADOWS
    };

    @SubscribeEvent
    public void onGui(InitGuiEvent.Post event) {
        if(event.gui instanceof GuiVideoSettings) {
            for (Object rowObject : ((GuiOptionsRowList)((GuiVideoSettings)event.gui).optionsRowList).field_148184_k) {
                GuiOptionsRowList.Row row = (GuiOptionsRowList.Row)rowObject;
                GuiButton buttonOne = row.field_148323_b;
                GuiButton buttonTwo = row.field_148324_c;
                if(buttonOne.id == GameSettings.Options.RENDER_CLOUDS.ordinal()) {
                    row.field_148324_c = new GuiCustomMenuButton(
                        buttonOne.id,
                        buttonOne.xPosition, buttonOne.yPosition,
                        buttonOne.width, buttonOne.height,
                        "NotFine Alpha",
                        new GuiNotFineSettings(event.gui, "NotFine Alpha Settings", settings)
                    );
                    break;
                } else if(buttonTwo != null && buttonTwo.id == GameSettings.Options.RENDER_CLOUDS.ordinal()) {
                    row.field_148324_c = new GuiCustomMenuButton(
                        buttonTwo.id,
                        buttonTwo.xPosition, buttonTwo.yPosition,
                        buttonTwo.width, buttonTwo.height,
                        "NotFine Alpha",
                        new GuiNotFineSettings(event.gui, "NotFine Alpha Settings", settings)
                    );
                    break;
                }
            }
        }
    }

}
