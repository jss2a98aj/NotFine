package jss.notfine.mixins.early.minecraft;

import jss.notfine.core.NotFineSettings;
import jss.util.RandomXoshiro256StarStar;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {

    @Redirect(method = "renderStars", at = @At(value = "NEW", target = "Ljava/util/Random;<init>(J)V", ordinal = 0, remap = false))
    private Random redirectRenderStarsRandom(long seed) {
        return new RandomXoshiro256StarStar(seed);
    }

    @Inject(method = "renderStars", at = @At("HEAD"), cancellable = true)
    void toggleStars(CallbackInfo ci) {
        if(NotFineSettings.starCount <= 0) ci.cancel();
    }

    @ModifyConstant(method = "renderStars", constant = @Constant(intValue = 1500))
    private static int setStarCount(int old) {
        return NotFineSettings.starCount;
    }

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    void toggleClouds(CallbackInfo ci) {
        if(NotFineSettings.cloudMode == 0) ci.cancel();
    }

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    void toggleSky(CallbackInfo ci) {
        if(!NotFineSettings.renderSky) ci.cancel();
    }

    //TODO: Make cloud fog work
    @Overwrite
    public boolean hasCloudFog(double p_72721_1_, double p_72721_3_, double p_72721_5_, float p_72721_7_) {
        return false;
    }

}
