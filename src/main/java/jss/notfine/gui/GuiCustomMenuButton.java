package jss.notfine.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiCustomMenuButton extends GuiButton {

    private final GuiScreen linkedMenu;

    public GuiCustomMenuButton(int id, int xPosition, int yPosition, int width, int height, String label, GuiScreen menu) {
        super(id, xPosition, yPosition, width, height, label);
        linkedMenu = menu;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean load = super.mousePressed(mc, mouseX, mouseY);
        if(load) {
            mc.displayGuiScreen(linkedMenu);
        }
        return load;
    }

}
