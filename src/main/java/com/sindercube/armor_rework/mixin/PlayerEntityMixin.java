package com.sindercube.armor_rework.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.sindercube.armor_rework.BarrierHandler;
import com.sindercube.armor_rework.content.components.BarrierAttachment;
import com.sindercube.armor_rework.asm.ArmorReworkLivingEntityAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.UnaryOperator;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ArmorReworkLivingEntityAccess {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}


	@Unique private int barrierRegenerationDelay = 0;
	@Unique private int previousArmor = 0;

	@Inject(method = "tick", at = @At("TAIL"))
	private void appendTick(CallbackInfo ci) {
		if (this.barrierRegenerationDelay <= 0) {
			BarrierHandler.regenerateBarrier(this);
			this.barrierRegenerationDelay = 60;
		}
		--this.barrierRegenerationDelay;

		var armor = this.getArmor();
		if (armor != previousArmor) {
			BarrierHandler.cutToMax(this);
			previousArmor = armor;
		}
	}

//	@Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", shift = At.Shift.BEFORE, by = 1))
//	private void takeBarrierDamageFirst(DamageSource source, float amount, CallbackInfo ci) {
//		doSomething3();
//	}

	@ModifyVariable(method = "applyDamage", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", shift = At.Shift.BEFORE, by = 1), ordinal = 1)
	private float takeBarrierDamageFirst(float damage, @Local(argsOnly = true) DamageSource source) {
		System.out.println(damage);
		return BarrierHandler.takeBarrierDamageFirst(this, source, damage);
	}

}
