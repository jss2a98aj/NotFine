package jss.notfine.mixins.early.minecraft.faceculling;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderBlocks.class)
public abstract class MixinRenderBlocks {

    @WrapOperation(
        method = "renderBlockFence(Lnet/minecraft/block/BlockFence;III)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderBlocks;renderStandardBlock(Lnet/minecraft/block/Block;III)Z",
            ordinal = 1
        )
    )
    public boolean renderBlockFenceSkipArm1(RenderBlocks instance, Block blockType, int blockX, int blockY, int blockZ, Operation<Boolean> original) {
        if(((BlockFence)blockType).canConnectFenceTo(blockAccess, blockX - 1, blockY, blockZ) || ((BlockFence)blockType).canConnectFenceTo(blockAccess, blockX + 1, blockY, blockZ)) {
            return original.call(instance, blockType, blockX, blockY, blockZ);
        }
        return false;
    }

    @WrapOperation(
        method = "renderBlockFence(Lnet/minecraft/block/BlockFence;III)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderBlocks;renderStandardBlock(Lnet/minecraft/block/Block;III)Z",
            ordinal = 3
        )
    )
    public boolean renderBlockFenceSkipArm2(RenderBlocks instance, Block blockType, int blockX, int blockY, int blockZ, Operation<Boolean> original) {
        if(((BlockFence)blockType).canConnectFenceTo(blockAccess, blockX - 1, blockY, blockZ) || ((BlockFence)blockType).canConnectFenceTo(blockAccess, blockX + 1, blockY, blockZ)) {
            return original.call(instance, blockType, blockX, blockY, blockZ);
        }
        return false;
    }

    /**
     * @author jss2a98aj, Roadhog360
     * @reason Cull faces against solid blocks, fix door smooth lighting
     */
    @Inject(
        at = @At("HEAD"),
        method = "renderBlockDoor(Lnet/minecraft/block/Block;III)Z",
        cancellable = true
    )
    public void renderBlockDoor(Block blockType, int blockX, int blockY, int blockZ, CallbackInfoReturnable<Boolean> cir) {
        final int meta = blockAccess.getBlockMetadata(blockX, blockY, blockZ);

        if((meta & 8) != 0) {
            if (blockAccess.getBlock(blockX, blockY - 1, blockZ) != blockType) {
                cir.setReturnValue(false);
            }
        } else if (blockAccess.getBlock(blockX, blockY + 1, blockZ) != blockType) {
            cir.setReturnValue(false);
        }

        if ((meta & 8) != 0) {
            uvRotateTop = meta % 6;
        }
        boolean flag = renderStandardBlock(blockType, blockX, blockY, blockZ);
        uvRotateTop = 0;
        cir.setReturnValue(flag);
    }

    /**
     * @author jss2a98aj
     * @reason Fix modded wall detection
     */
    @Redirect(
        method = "renderBlockFenceGate(Lnet/minecraft/block/BlockFenceGate;III)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/IBlockAccess;getBlock(III)Lnet/minecraft/block/Block;"
        ),
        expect = 4
    )
    Block detectModdedWalls(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z) instanceof BlockWall ? Blocks.cobblestone_wall : null;
    }

    @Shadow public IBlockAccess blockAccess;
    @Shadow public int uvRotateTop;
    @Shadow public abstract boolean renderStandardBlock(Block blockType, int blockX, int blockY, int blockZ);

}
