package jss.notfine.gui;

import jss.notfine.core.NotFineSettings;
import net.minecraft.client.gui.GuiButton;

public class GuiNotFineSettingButton extends GuiButton {
    public final NotFineSettings.Settings setting;

    public GuiNotFineSettingButton(int xPosition, int yPosition, NotFineSettings.Settings setting) {
        super(setting.ordinal(), xPosition, yPosition, 150, 20, setting.getLocalization());
        this.setting = setting;
    }

}
