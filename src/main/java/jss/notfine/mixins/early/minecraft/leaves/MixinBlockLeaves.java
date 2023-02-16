package jss.notfine.mixins.early.minecraft.leaves;

import jss.notfine.core.Settings;
import jss.notfine.core.SettingsManager;
import jss.util.DirectionHelper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockLeaves.class)
public abstract class MixinBlockLeaves extends BlockLeavesBase {

    /**
     * @author jss2a98aj
     * @reason Control leaf opacity.
     */
    @Overwrite
    public boolean isOpaqueCube() {
        return SettingsManager.leavesOpaque;
    }

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int renderMode = (int) Settings.MODE_LEAVES.getValue();
        if(renderMode >= 0 && field_150129_M[0] != null) {
            if(renderMode == 4) {
                renderMode = world.getBlock(
                    x + DirectionHelper.xDirectionalIncrease[side],
                    y + DirectionHelper.yDirectionalIncrease[side],
                    z + DirectionHelper.zDirectionalIncrease[side]
                ) instanceof BlockLeavesBase ? 1 : 0;
            }
            renderMode = renderMode > 1 ? 0 : renderMode;
            int maskedMeta = world.getBlockMetadata(x, y, z) & 3;
            maskedMeta = maskedMeta >= field_150129_M[renderMode].length ? 0 : maskedMeta;
            return field_150129_M[renderMode][maskedMeta];
        } else {
            //Default fallback or a mod dev had no idea what they were doing.
            return getIcon(side, world.getBlockMetadata(x, y, z));
        }
    }

    @Shadow protected IIcon[][] field_150129_M;

    protected MixinBlockLeaves(Material material, boolean overridden) {
        super(material, overridden);
    }

}
