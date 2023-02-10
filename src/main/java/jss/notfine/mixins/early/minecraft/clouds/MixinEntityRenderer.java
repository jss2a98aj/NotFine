package jss.notfine.mixins.early.minecraft.clouds;

import jss.notfine.core.NotFineSettings;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = EntityRenderer.class, priority = 990)
public abstract class MixinEntityRenderer {

    @ModifyConstant(method = "renderWorld", constant = @Constant(doubleValue = 128.0D), expect = 2)
    double modifyCloudHeightCheck(double original) {
        return NotFineSettings.cloudTranslucencyCheck;
    }

    @ModifyArg(method = "setupCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V", remap = false), index = 3)
    private float modifyFarPlane(float original) {
        return Math.max(original, NotFineSettings.minimumFarPlaneDistance);
    }

}
