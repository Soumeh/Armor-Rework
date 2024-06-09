package com.sindercube.armor_rework.mixin;

import com.sindercube.armor_rework.BarrierAttachment;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Unique
	private int barrierRegenerationDelay = 0;


	@Inject(method = "tick", at = @At("TAIL"))
	private void injected(CallbackInfo ci) {
		PlayerEntity self = (PlayerEntity)(Object)this;
		var component = BarrierAttachment.get(self);

		if (this.barrierRegenerationDelay <= 0) {
			component.regenerate(self);
			this.barrierRegenerationDelay = 60;
		}
		if (this.barrierRegenerationDelay % 5 == 0) {
			component.modifyBarrier(e -> e-1);
		}
		--this.barrierRegenerationDelay;
	}

}
