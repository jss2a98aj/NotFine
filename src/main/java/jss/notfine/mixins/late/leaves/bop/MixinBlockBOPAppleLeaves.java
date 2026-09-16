package jss.notfine.mixins.late.leaves.bop;

import biomesoplenty.common.blocks.BlockBOPAppleLeaves;
import jss.notfine.util.LeafRenderUtil;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockBOPAppleLeaves.class)
public abstract class MixinBlockBOPAppleLeaves extends BlockLeavesBase {

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int maskedMeta = world.getBlockMetadata(x, y, z) & 3;
        final int renderMode = LeafRenderUtil.selectRenderMode(world, x, y, z, side) ? 1 : 0;
        maskedMeta = maskedMeta > 1 ? 0 : maskedMeta;
        return textures[renderMode][maskedMeta];
    }

    /**
     * @author jss2a98aj
     * @reason Support new leaf rendering modes on BOP leaves.
     */
    @Override
    @Overwrite
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return LeafRenderUtil.shouldSideBeRendered(world, x, y, z, side);
    }

    @Shadow(remap = false)
    private IIcon[][] textures;

    private MixinBlockBOPAppleLeaves(Material material, boolean overridden) {
        super(material, overridden);
    }
}
