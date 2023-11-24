package jss.notfine.util;

import jss.notfine.core.Settings;
import jss.notfine.core.SettingsManager;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface ILeafBlock extends IFaceObstructionCheckHelper {

    @Override()
    default boolean isFaceNonObstructing(IBlockAccess worldIn, int x, int y, int z, int side, double otherMinX, double otherMinY, double otherMinZ, double otherMaxX, double otherMaxY, double otherMaxZ) {
        if((int) Settings.MODE_LEAVES.getValue() == 4) {
            Block otherBlock;
            otherBlock = worldIn.getBlock(x + 1, y, z);
            if(!otherBlock.isOpaqueCube() && !(otherBlock instanceof ILeafBlock)) {
                return true;
            }
            otherBlock = worldIn.getBlock(x - 1, y, z);
            if(!otherBlock.isOpaqueCube() && !(otherBlock instanceof ILeafBlock)) {
                return true;
            }
            otherBlock = worldIn.getBlock(x, y + 1, z);
            if(!otherBlock.isOpaqueCube() && !(otherBlock instanceof ILeafBlock)) {
                return true;
            }
            otherBlock = worldIn.getBlock(x, y - 1, z);
            if(!otherBlock.isOpaqueCube() && !(otherBlock instanceof ILeafBlock)) {
                return true;
            }
            otherBlock = worldIn.getBlock(x, y, z + 1);
            if(!otherBlock.isOpaqueCube() && !(otherBlock instanceof ILeafBlock)) {
                return true;
            }
            otherBlock = worldIn.getBlock(x, y, z - 1);
            return !otherBlock.isOpaqueCube() && !(otherBlock instanceof ILeafBlock);
        } else {
            return !SettingsManager.leavesOpaque;
        }
    }

}
