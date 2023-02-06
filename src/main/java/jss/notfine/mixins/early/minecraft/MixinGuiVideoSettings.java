package jss.notfine.mixins.early.minecraft;

import net.minecraft.client.gui.GuiVideoSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(value = GuiVideoSettings.class)
public abstract class MixinGuiVideoSettings {
    //@Inject()


}
