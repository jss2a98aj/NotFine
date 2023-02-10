package jss.notfine.mixins.early.minecraft.clouds;

import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = GameSettings.class)
public abstract class MixinGameSettings {

    public boolean shouldRenderClouds() {
        return clouds;
    }

    @Shadow public boolean clouds;

}
