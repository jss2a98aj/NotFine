package jss.notfine.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiNotFineMenuButton extends GuiButton {

    private final GuiScreen menu;

    public GuiNotFineMenuButton(int id, int xPosition, int yPosition, int width, int height, String label, GuiScreen menu) {
        super(id, xPosition, yPosition, width, height, label);
        this.menu = menu;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean load = super.mousePressed(mc, mouseX, mouseY);
        if(load) {
            mc.displayGuiScreen(menu);
        }
        return load;
    }

}
