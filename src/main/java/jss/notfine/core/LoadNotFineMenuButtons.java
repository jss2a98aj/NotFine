package jss.notfine.core;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jss.notfine.gui.GuiNotFineMenuButton;
import jss.notfine.gui.GuiNotFineSettings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;

public class LoadNotFineMenuButtons {

    public static final LoadNotFineMenuButtons INSTANCE = new LoadNotFineMenuButtons();

    private final NotFineSettings.Settings[] settings = new NotFineSettings.Settings[] {
        NotFineSettings.Settings.MODE_SKY, NotFineSettings.Settings.MODE_LEAVES,
        NotFineSettings.Settings.MODE_CLOUDS, NotFineSettings.Settings.RENDER_DISTANCE_CLOUDS,
        NotFineSettings.Settings.CLOUD_SCALE, NotFineSettings.Settings.CLOUD_HEIGHT,
        NotFineSettings.Settings.MODE_CLOUD_TRANSLUCENCY, NotFineSettings.Settings.PARTICLES_ENC_TABLE,
        NotFineSettings.Settings.MODE_GLEAM_WORLD, NotFineSettings.Settings.MODE_GLEAM_INV,
        NotFineSettings.Settings.PARTICLES_VOID
    };

    @SubscribeEvent
    public void onGui(InitGuiEvent.Post event) {
        if(event.gui instanceof GuiVideoSettings) {
            for (Object rowObject : ((GuiOptionsRowList)((GuiVideoSettings)event.gui).optionsRowList).field_148184_k) {
                GuiOptionsRowList.Row row = (GuiOptionsRowList.Row)rowObject;
                GuiButton buttonOne = row.field_148323_b;
                GuiButton buttonTwo = row.field_148324_c;
                if(buttonOne.id == GameSettings.Options.RENDER_CLOUDS.ordinal()) {
                    row.field_148324_c = new GuiNotFineMenuButton(
                        buttonOne.id,
                        buttonOne.xPosition, buttonOne.yPosition,
                        buttonOne.width, buttonOne.height,
                        "NotFine Alpha",
                        new GuiNotFineSettings(event.gui, "NotFine Alpha Settings", settings)
                    );
                    break;
                } else if(buttonTwo != null && buttonTwo.id == GameSettings.Options.RENDER_CLOUDS.ordinal()) {
                    row.field_148324_c = new GuiNotFineMenuButton(
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
