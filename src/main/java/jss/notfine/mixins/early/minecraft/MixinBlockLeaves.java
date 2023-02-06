package jss.notfine.mixins.early.minecraft;

import jss.notfine.core.NotFineSettings;
import net.minecraft.block.BlockLeaves;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockLeaves.class)
public abstract class MixinBlockLeaves {

    @Overwrite
    public boolean isOpaqueCube() {
        //Todo: update original value at runtime rather than using an Overwrite.
        return NotFineSettings.leavesRenderMode == 1;
    }

}
