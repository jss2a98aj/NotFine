package jss.notfine.mixins.late.thaumcraft.leaves;

import jss.notfine.util.ILeafBlock;
import jss.notfine.core.Settings;
import jss.notfine.core.SettingsManager;
import jss.util.DirectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import thaumcraft.common.blocks.BlockMagicalLeaves;

@Mixin(value = BlockMagicalLeaves.class)
public abstract class MixinBlockMagicalLeaves extends Block implements ILeafBlock {

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int renderMode = (int) Settings.MODE_LEAVES.getValue();
        if(renderMode >= 0) {
            if(renderMode == 4) {
                renderMode = world.getBlock(
                    x + DirectionHelper.xDirectionalIncrease[side],
                    y + DirectionHelper.yDirectionalIncrease[side],
                    z + DirectionHelper.zDirectionalIncrease[side]
                ) instanceof ILeafBlock ? 1 : 0;
            }
            renderMode = renderMode > 1 ? 0 : renderMode;
            int maskedMeta = world.getBlockMetadata(x, y, z) & 3;
            maskedMeta = maskedMeta >= 2 ? 0 : maskedMeta;
            return icon[renderMode + maskedMeta * 2];
        } else {
            //Default fallback.
            return getIcon(side, world.getBlockMetadata(x, y, z));
        }
    }

    /**
     * @author jss2a98aj
     * @reason Support new leaf rendering modes on Thaumcraft leaves.
     */
    @Overwrite
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block otherBlock = world.getBlock(x, y, z);
        if (otherBlock.isOpaqueCube()) {
            return false;
        }
        switch((int) Settings.MODE_LEAVES.getValue()) {
            case 1:
            case 2:
                return !(otherBlock instanceof ILeafBlock);
            case 3:
            case 4:
                if(otherBlock instanceof ILeafBlock) {
                    x -= DirectionHelper.xDirectionalIncrease[side];
                    y -= DirectionHelper.yDirectionalIncrease[side];
                    z -= DirectionHelper.zDirectionalIncrease[side];
                    int renderCheck = 0;
                    otherBlock = world.getBlock(x + 1, y, z);
                    if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x - 1, y, z);
                    if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y + 1, z);
                    if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y - 1, z);
                    if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y, z + 1);
                    if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y, z - 1);
                    if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    boolean renderSide = renderCheck == 6;
                    if(renderSide) {
                        x += 2 * DirectionHelper.xDirectionalIncrease[side];
                        y += 2 * DirectionHelper.yDirectionalIncrease[side];
                        z += 2 * DirectionHelper.zDirectionalIncrease[side];
                        otherBlock = world.getBlock(x, y, z);
                        if(((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                            renderSide = false;
                        }
                        x -= DirectionHelper.xDirectionalIncrease[side];
                        y -= DirectionHelper.yDirectionalIncrease[side];
                        z -= DirectionHelper.zDirectionalIncrease[side];
                        int nextSide = DirectionHelper.relativeADirections[side];
                        otherBlock = world.getBlock(
                            x + DirectionHelper.xDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                        nextSide = DirectionHelper.relativeBDirections[side];
                        otherBlock = world.getBlock(
                            x + DirectionHelper.xDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                        nextSide = DirectionHelper.relativeCDirections[side];
                        otherBlock = world.getBlock(
                            x + DirectionHelper.xDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                        nextSide = DirectionHelper.relativeDDirections[side];
                        otherBlock = world.getBlock(
                            x + DirectionHelper.xDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof ILeafBlock) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                    }
                    return renderSide;
                }
            default:
                return !SettingsManager.leavesOpaque || !(otherBlock instanceof ILeafBlock);
        }
    }

    protected MixinBlockMagicalLeaves(Material material) {
        super(material);
    }

    @Shadow(remap = false)
    public IIcon[] icon;

}
