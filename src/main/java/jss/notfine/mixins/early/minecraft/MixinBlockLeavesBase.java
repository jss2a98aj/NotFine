package jss.notfine.mixins.early.minecraft;

import jss.notfine.core.NotFineSettings;
import jss.util.DirectionHelper;
import jss.util.Directions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = BlockLeavesBase.class)
public abstract class MixinBlockLeavesBase extends Block {

    protected MixinBlockLeavesBase(Material materialIn) {
        super(materialIn);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block otherBlock = world.getBlock(x, y, z);
        if (otherBlock.isOpaqueCube()) {
            return false;
        }
        switch(NotFineSettings.leavesRenderMode) {
            case 1:
            case 2:
                return !(otherBlock instanceof BlockLeavesBase);
            case 3:
            case 4:
                if(otherBlock instanceof BlockLeaves) {
                    x -= DirectionHelper.xDirectionalIncrease[side];
                    y -= DirectionHelper.yDirectionalIncrease[side];
                    z -= DirectionHelper.zDirectionalIncrease[side];
                    int renderCheck = 0;
                    otherBlock = world.getBlock(x + 1, y, z);
                    if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x - 1, y, z);
                    if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y + 1, z);
                    if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y - 1, z);
                    if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y, z + 1);
                    if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    otherBlock = world.getBlock(x, y, z - 1);
                    if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                        renderCheck++;
                    }
                    boolean renderSide = renderCheck == 6;
                    if(renderSide) {
                        x += 2 * DirectionHelper.xDirectionalIncrease[side];
                        y += 2 * DirectionHelper.yDirectionalIncrease[side];
                        z += 2 * DirectionHelper.zDirectionalIncrease[side];
                        otherBlock = world.getBlock(x, y, z);
                        if(((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                            renderSide = false;
                        }
                        x -= DirectionHelper.xDirectionalIncrease[side];
                        y -= DirectionHelper.yDirectionalIncrease[side];
                        z -= DirectionHelper.zDirectionalIncrease[side];
                        int nextSide = DirectionHelper.relativeADirections[side].ordinal();
                        otherBlock = world.getBlock(
                            x + DirectionHelper.zDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                        nextSide = DirectionHelper.relativeBDirections[side].ordinal();
                        otherBlock = world.getBlock(
                            x + DirectionHelper.zDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                        nextSide = DirectionHelper.relativeCDirections[side].ordinal();
                        otherBlock = world.getBlock(
                            x + DirectionHelper.zDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                        nextSide = DirectionHelper.relativeDDirections[side].ordinal();
                        otherBlock = world.getBlock(
                            x + DirectionHelper.zDirectionalIncrease[nextSide],
                            y + DirectionHelper.yDirectionalIncrease[nextSide],
                            z + DirectionHelper.zDirectionalIncrease[nextSide]
                        );
                        if(!((otherBlock instanceof BlockLeavesBase) || otherBlock.isOpaqueCube())) {
                            renderSide = true;
                        }
                    }
                    return renderSide;
                }
        }
        return true;
    }

}
