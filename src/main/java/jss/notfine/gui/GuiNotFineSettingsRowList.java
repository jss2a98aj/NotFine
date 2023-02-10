package jss.notfine.gui;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jss.notfine.core.NotFineSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiNotFineSettingsRowList extends GuiListExtended {
    private final List settingsList = Lists.newArrayList();

    public GuiNotFineSettingsRowList(Minecraft mc, int width, int height, int top, int bottom, int slotHeight, NotFineSettings.Settings ... settings)  {
        super(mc, width, height, top, bottom, slotHeight);
        field_148163_i = false;

        for (int i = 0; i < settings.length; i += 2) {
            NotFineSettings.Settings settingOne = settings[i];
            NotFineSettings.Settings settingTwo = i < settings.length - 1 ? settings[i + 1] : null;
            GuiButton buttonOne = createButton(width / 2 - 155, 0, settingOne);
            GuiButton buttonTwo = createButton(width / 2 - 155 + 160, 0, settingTwo);
            settingsList.add(new GuiNotFineSettingsRowList.Row(buttonOne, buttonTwo));
        }
    }

    private GuiButton createButton(int xPosition, int yPosition, NotFineSettings.Settings setting) {
        if (setting == null) {
            return null;
        } else {
            return setting.slider ? new GuiNotFineSettingSlider(xPosition, yPosition, setting) : new GuiNotFineSettingButton(xPosition, yPosition, setting);
        }
    }

    @Override
    public GuiNotFineSettingsRowList.Row getListEntry(int index) {
        return (GuiNotFineSettingsRowList.Row) settingsList.get(index);
    }

    @Override
    protected int getSize() {
        return settingsList.size();
    }

    @Override
    public int getListWidth() {
        return 400;
    }

    @Override
    protected int getScrollBarX() {
        return super.getScrollBarX() + 32;
    }

    @SideOnly(Side.CLIENT)
    public static class Row implements GuiListExtended.IGuiListEntry {
        private final Minecraft mc = Minecraft.getMinecraft();
        private final GuiButton buttonOne, buttonTwo;

        public Row(GuiButton one, GuiButton two) {
            buttonOne = one;
            buttonTwo = two;
        }

        @Override
        public void drawEntry(int varU1, int x, int y, int varU2, int varU3, Tessellator tessellator, int mouseX, int mouseY, boolean varU4)  {
            if (buttonOne != null) {
                buttonOne.yPosition = y;
                buttonOne.drawButton(mc, mouseX, mouseY);
            }

            if (buttonTwo != null) {
                buttonTwo.yPosition = y;
                buttonTwo.drawButton(mc, mouseX, mouseY);
            }
        }

        @Override
        public boolean mousePressed(int index, int x, int y, int mouseEvent, int relativeX, int relativeY) {
            if(buttonOne.mousePressed(mc, x, y)) {
                if(buttonOne instanceof GuiNotFineSettingButton) {
                    NotFineSettings.Settings setting = ((GuiNotFineSettingButton) buttonOne).setting;
                    setting.incrementValue();
                    buttonOne.displayString = setting.getLocalization();
                }
                return true;
            } else if(buttonTwo != null && buttonTwo.mousePressed(mc, x, y)) {
                if(buttonTwo instanceof GuiNotFineSettingButton) {
                    NotFineSettings.Settings setting = ((GuiNotFineSettingButton) buttonTwo).setting;
                    setting.incrementValue();
                    buttonTwo.displayString = setting.getLocalization();
                }
                return true;
            }
            return false;
        }

        @Override
        public void mouseReleased(int index, int x, int y, int mouseEvent, int relativeX, int relativeY) {
            if (buttonOne != null) {
                buttonOne.mouseReleased(x, y);
            }

            if (buttonTwo != null) {
                buttonTwo.mouseReleased(x, y);
            }
        }
    }

}
