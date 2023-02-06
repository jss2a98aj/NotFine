package jss.notfine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiDetailSettings extends GuiScreen {
    private GuiScreen parentGuiScreen;
    protected String screenTitle = "Detail Settings";
    private GameSettings guiDetailSettings;
    //private GuiListExtended optionsRowList;

    public GuiDetailSettings(GuiScreen parentGuiScreen, GameSettings guiDetailSettings) {
        this.parentGuiScreen = parentGuiScreen;
        this.guiDetailSettings = guiDetailSettings;
    }

    @Override
    public void initGui() {
        screenTitle = I18n.format("options.detailTitle", new Object[0]);
        buttonList.clear();
        buttonList.add(new GuiButton(200, width / 2 - 100, height - 27, I18n.format("gui.done", new Object[0])));

        //optionsRowList = new GuiOptionsRowList(mc, width, height, 32, height - 32, 25, videoOptions);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled && button.id == 200) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(parentGuiScreen);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        //optionsRowList.drawScreen(mouseX, mouseY, partialTicks);
        drawCenteredString(fontRendererObj, screenTitle, width / 2, 5, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
