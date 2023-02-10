package jss.notfine.mixins.early.minecraft.clouds;

import jss.notfine.core.NotFineSettings;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = EntityRenderer.class, priority = 990)
public abstract class MixinEntityRenderer {

    @ModifyArg(method = "setupCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V", remap = false), index = 3)
    private float modifyFarPlane(float original) {
        return Math.max(original, NotFineSettings.minimumFarPlaneDistance);
    }

    @Shadow private float farPlaneDistance;

}
