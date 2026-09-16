package jss.notfine.mixins.late.leaves.bop;

import biomesoplenty.common.blocks.BlockBOPLeaves;
import jss.notfine.util.LeafRenderUtil;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockBOPLeaves.class)
public abstract class MixinBlockBOPLeaves extends BlockLeavesBase {

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        final int type = getTypeFromMeta(world.getBlockMetadata(x, y, z)) + category.ordinal() * 4;
        final int renderMode = LeafRenderUtil.selectRenderMode(world, x, y, z, side) ? 1 : 0;
        return textures[renderMode][type >= leaves.length ? 0 : type];
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
    @Final
    private static String[] leaves;

    @Shadow(remap = false)
    private IIcon[][] textures;

    @Shadow(remap = false)
    @Final
    private BlockBOPLeaves.LeafCategory category;

    private MixinBlockBOPLeaves(Material material, boolean overridden) {
        super(material, overridden);
    }

    @Shadow(remap = false)
    private static int getTypeFromMeta(int meta) { return 0; };

}
