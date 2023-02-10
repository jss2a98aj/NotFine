package jss.notfine.mixins.early.minecraft;

import jss.notfine.core.NotFineSettings;
import net.minecraft.world.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = WorldType.class)
public abstract class MixinWorldType {

    /**
     * @author jss2a98aj
     * @reason Control cloud height.
     */
    @Overwrite(remap = false)
    public float getCloudHeight() {
        return NotFineSettings.Settings.CLOUD_HEIGHT.getValue();
    }

}
