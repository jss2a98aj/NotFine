package jss.notfine.mixins.early.minecraft.faceculling;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = RenderBlocks.class)
public abstract class MixinRenderBlocks {

    /**
     * @author jss2a98aj
     * @reason Culling a few more faces
     */
    @Overwrite
    public boolean renderBlockFence(BlockFence fence, int x, int y, int z) {
        float min = 0.375F;
        float max = 0.625F;
        setRenderBounds(min, 0.0D, min, max, 1.0D, max);
        renderStandardBlock(fence, x, y, z);

        boolean connectXNeg = fence.canConnectFenceTo(blockAccess, x - 1, y, z);
        boolean connectXPos = fence.canConnectFenceTo(blockAccess, x + 1, y, z);
        boolean connectZNeg = fence.canConnectFenceTo(blockAccess, x, y, z - 1);
        boolean connectZPos = fence.canConnectFenceTo(blockAccess, x, y, z + 1);
        boolean connectX = connectXNeg || connectXPos;
        boolean connectZ = connectZNeg || connectZPos;

        min = 0.4375F;
        max = 0.5625F;
        float minY = 0.75F;
        float maxY = 0.9375F;
        float minX = connectXNeg ? 0.0F : min;
        float maxX = connectXPos ? 1.0F : max;
        float minZ = connectZNeg ? 0.0F : min;
        float maxZ = connectZPos ? 1.0F : max;
        field_152631_f = true;
        //Upper beam
        if(connectX) {
            setRenderBounds(minX, minY, min, maxX, maxY, max);
            renderStandardBlock(fence, x, y, z);
        }
        if(connectZ) {
            setRenderBounds(min, minY, minZ, max, maxY, maxZ);
            renderStandardBlock(fence, x, y, z);
        }

        minY = 0.375F;
        maxY = 0.5625F;
        //Lower beam
        if(connectX) {
            setRenderBounds(minX, minY, min, maxX, maxY, max);
            renderStandardBlock(fence, x, y, z);
        }
        if(connectZ) {
            setRenderBounds(min, minY, minZ, max, maxY, maxZ);
            renderStandardBlock(fence, x, y, z);
        }

        field_152631_f = false;
        fence.setBlockBoundsBasedOnState(blockAccess, x, y, z);
        return true;
    }

    /**
     * @author jss2a98aj
     * @reason Culling a few more faces
     */
    @Overwrite
    public boolean renderBlockFenceGate(BlockFenceGate fenceGate, int x, int y, int z) {
        int meta = blockAccess.getBlockMetadata(x, y, z);
        boolean isOpen = BlockFenceGate.isFenceGateOpen(meta);
        int direction = BlockDirectional.getDirection(meta);
        float f = 0.375F;
        float f1 = 0.5625F;
        float f2 = 0.75F;
        float f3 = 0.9375F;
        float postBot = 0.3125F;
        float postTop = 1.0F;

        if((direction == 2 || direction == 0) && blockAccess.getBlock(x - 1, y, z) instanceof BlockWall && blockAccess.getBlock(x + 1, y, z) instanceof BlockWall || (direction == 3 || direction == 1) && blockAccess.getBlock(x, y, z - 1) instanceof BlockWall && blockAccess.getBlock(x, y, z + 1) instanceof BlockWall) {
            f -= 0.1875F;
            f1 -= 0.1875F;
            f2 -= 0.1875F;
            f3 -= 0.1875F;
            postBot -= 0.1875F;
            postTop -= 0.1875F;
        }

        renderAllFaces = true;
        float f6;
        float f7;
        float f8;
        float f9;

        if(direction != 3 && direction != 1) {
            f6 = 0.0F;
            f7 = 0.125F;
            f8 = 0.4375F;
            f9 = 0.5625F;
            setRenderBounds(f6, postBot, f8, f7, postTop, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f6 = 0.875F;
            f7 = 1.0F;
            setRenderBounds(f6, postBot, f8, f7, postTop, f9);
            renderStandardBlock(fenceGate, x, y, z);
        } else {
            uvRotateTop = 1;
            f6 = 0.4375F;
            f7 = 0.5625F;
            f8 = 0.0F;
            f9 = 0.125F;
            setRenderBounds(f6, postBot, f8, f7, postTop, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f8 = 0.875F;
            f9 = 1.0F;
            setRenderBounds(f6, postBot, f8, f7, postTop, f9);
            renderStandardBlock(fenceGate, x, y, z);
            uvRotateTop = 0;
        }

        if(isOpen) {
            if(direction == 2 || direction == 0) {
                uvRotateTop = 1;
            }

            float f10;
            float f11;
            float f12;

            if(direction == 3) {
                f6 = 0.0F;
                f7 = 0.125F;
                f8 = 0.875F;
                f9 = 1.0F;
                f10 = 0.5625F;
                f11 = 0.8125F;
                f12 = 0.9375F;
                setRenderBounds(f11, f, f6, f12, f3, f7);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f11, f, f8, f12, f3, f9);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f10, f, f6, f11, f1, f7);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f10, f, f8, f11, f1, f9);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f10, f2, f6, f11, f3, f7);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f10, f2, f8, f11, f3, f9);
                renderStandardBlock(fenceGate, x, y, z);
            } else if(direction == 1) {
                f6 = 0.0F;
                f7 = 0.125F;
                f8 = 0.875F;
                f9 = 1.0F;
                f10 = 0.0625F;
                f11 = 0.1875F;
                f12 = 0.4375F;
                setRenderBounds(f10, f, f6, f11, f3, f7);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f10, f, f8, f11, f3, f9);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f11, f, f6, f12, f1, f7);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f11, f, f8, f12, f1, f9);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f11, f2, f6, f12, f3, f7);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f11, f2, f8, f12, f3, f9);
                renderStandardBlock(fenceGate, x, y, z);
            } else if (direction == 0) {
                f6 = 0.0F;
                f7 = 0.125F;
                f8 = 0.875F;
                f9 = 1.0F;
                f10 = 0.5625F;
                f11 = 0.8125F;
                f12 = 0.9375F;
                setRenderBounds(f6, f, f11, f7, f3, f12);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f8, f, f11, f9, f3, f12);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f6, f, f10, f7, f1, f11);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f8, f, f10, f9, f1, f11);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f6, f2, f10, f7, f3, f11);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f8, f2, f10, f9, f3, f11);
                renderStandardBlock(fenceGate, x, y, z);
            } else if (direction == 2) {
                f6 = 0.0F;
                f7 = 0.125F;
                f8 = 0.875F;
                f9 = 1.0F;
                f10 = 0.0625F;
                f11 = 0.1875F;
                f12 = 0.4375F;
                setRenderBounds(f6, f, f10, f7, f3, f11);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f8, f, f10, f9, f3, f11);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f6, f, f11, f7, f1, f12);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f8, f, f11, f9, f1, f12);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f6, f2, f11, f7, f3, f12);
                renderStandardBlock(fenceGate, x, y, z);
                setRenderBounds(f8, f2, f11, f9, f3, f12);
                renderStandardBlock(fenceGate, x, y, z);
            }
        } else if (direction != 3 && direction != 1) {
            f6 = 0.375F;
            f7 = 0.5F;
            f8 = 0.4375F;
            f9 = 0.5625F;
            setRenderBounds(f6, f, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f6 = 0.5F;
            f7 = 0.625F;
            setRenderBounds(f6, f, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f6 = 0.625F;
            f7 = 0.875F;
            setRenderBounds(f6, f, f8, f7, f1, f9);
            renderStandardBlock(fenceGate, x, y, z);
            setRenderBounds(f6, f2, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f6 = 0.125F;
            f7 = 0.375F;
            setRenderBounds(f6, f, f8, f7, f1, f9);
            renderStandardBlock(fenceGate, x, y, z);
            setRenderBounds(f6, f2, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
        } else {
            uvRotateTop = 1;
            f6 = 0.4375F;
            f7 = 0.5625F;
            f8 = 0.375F;
            f9 = 0.5F;
            setRenderBounds(f6, f, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f8 = 0.5F;
            f9 = 0.625F;
            setRenderBounds(f6, f, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f8 = 0.625F;
            f9 = 0.875F;
            setRenderBounds(f6, f, f8, f7, f1, f9);
            renderStandardBlock(fenceGate, x, y, z);
            setRenderBounds(f6, f2, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
            f8 = 0.125F;
            f9 = 0.375F;
            setRenderBounds(f6, f, f8, f7, f1, f9);
            renderStandardBlock(fenceGate, x, y, z);
            setRenderBounds(f6, f2, f8, f7, f3, f9);
            renderStandardBlock(fenceGate, x, y, z);
        }

        renderAllFaces = false;
        uvRotateTop = 0;
        setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return true;
    }

    @Shadow public IBlockAccess blockAccess;
    @Shadow public boolean field_152631_f;
    @Shadow public boolean renderAllFaces;
    @Shadow public int uvRotateTop;
    @Shadow public abstract void setRenderBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
    @Shadow public abstract void setRenderBoundsFromBlock(Block block);
    @Shadow public abstract boolean renderStandardBlock(Block blockType, int blockX, int blockY, int blockZ);

}
