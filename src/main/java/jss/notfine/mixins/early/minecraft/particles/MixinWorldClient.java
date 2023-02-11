package jss.notfine.mixins.early.minecraft.particles;

import java.util.Random;

import jss.notfine.core.NotFineSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldClient.class)
public abstract class MixinWorldClient {

	/**
	 * @author jss2a98aj
	 * @reason Xoshiro256** is faster than Random.
	 */
	@Redirect(method = "doVoidFogParticles", at = @At(value = "NEW", target = "java/util/Random", ordinal = 0))
	private Random notFine$redirectDoVoidFogParticlesRandom() {
		return new RandomXoshiro256StarStar();
	}

    @Inject(method = "doVoidFogParticles", at = @At("HEAD"), cancellable = true)
    void notFine$toggleVoidParticles(CallbackInfo ci) {
        if(!NotFineSettings.Settings.PARTICLES_VOID.isValueBase()) ci.cancel();
    }

}
