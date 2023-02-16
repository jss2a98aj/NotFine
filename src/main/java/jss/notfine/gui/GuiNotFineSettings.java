package jss.notfine.gui;

import jss.notfine.core.Settings;
import jss.notfine.core.SettingsManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiNotFineSettings extends GuiScreen {
    private final GuiScreen parentGuiScreen;
    private final String titleUnlocalized;
    private final Settings[] settings;
    protected String screenTitle = "Detail Settings";

    private GuiListExtended optionsRowList;

    public GuiNotFineSettings(GuiScreen parentGuiScreen, String titleUnlocalized, Settings... settings) {
        this.parentGuiScreen = parentGuiScreen;
        this.titleUnlocalized = titleUnlocalized;
        this.settings = settings;
    }

    @Override
    public void initGui() {
        screenTitle = I18n.format(titleUnlocalized);
        buttonList.clear();
        buttonList.add(new GuiButton(200, width / 2 - 100, height - 27, I18n.format("gui.done")));

        optionsRowList = new GuiNotFineSettingsRowList(mc, width, height, 32, height - 32, 25, settings);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled && button.id == 200) {
            mc.gameSettings.saveOptions();
            SettingsManager.settingsFile.saveSettings();
            mc.displayGuiScreen(parentGuiScreen);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        optionsRowList.func_148179_a(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseMovedOrUp(int mouseX, int mouseY, int state) {
        super.mouseMovedOrUp(mouseX, mouseY, state);
        optionsRowList.func_148181_b(mouseX, mouseY, state);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        optionsRowList.drawScreen(mouseX, mouseY, partialTicks);
        drawCenteredString(fontRendererObj, screenTitle, width / 2, 5, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
