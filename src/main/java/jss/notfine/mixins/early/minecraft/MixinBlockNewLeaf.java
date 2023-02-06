package jss.notfine.mixins.early.minecraft;

import jss.notfine.core.NotFineSettings;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = BlockNewLeaf.class)
public abstract class MixinBlockNewLeaf extends BlockLeaves {

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if(field_150129_M[0] != null) {
            int renderMode = NotFineSettings.leavesRenderMode;
            if(renderMode == 3) {
                int xPos = x, yPos = y, zPos = z;
                switch (side) {
                    case 0:// -y
                        yPos--;
                        break;
                    case 1:// +y
                        yPos++;
                        break;
                    case 2:// -z
                        zPos--;
                        break;
                    case 3:// +z
                        zPos++;
                        break;
                    case 4:// -x
                        xPos--;
                        break;
                    case 5:// +x
                        xPos++;
                }
                renderMode = world.getBlock(xPos, yPos, zPos) instanceof BlockLeavesBase ? 1 : 0;
            }
            renderMode = renderMode > 1 ? 0 : renderMode;
            return (world.getBlockMetadata(x, y, z) & 3) == 1 ? field_150129_M[renderMode][1] : field_150129_M[renderMode][0];
        } else {
            //This should only happen if a mod dev had no idea what they were doing.
            return getIcon(world.getBlockMetadata(x, y, z), side);
        }
    }

}
