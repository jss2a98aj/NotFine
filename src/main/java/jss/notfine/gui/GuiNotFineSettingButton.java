package jss.notfine.gui;

import jss.notfine.core.Settings;
import net.minecraft.client.gui.GuiButton;

public class GuiNotFineSettingButton extends GuiButton {
    public final Settings setting;

    public GuiNotFineSettingButton(int xPosition, int yPosition, Settings setting) {
        super(setting.ordinal(), xPosition, yPosition, 150, 20, setting.getLocalization());
        this.setting = setting;
    }

}
