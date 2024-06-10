package com.sindercube.armor_rework.mixin;

import com.sindercube.armor_rework.asm.ArmorReworkLivingEntityAccess;
import com.sindercube.armor_rework.content.components.BarrierAttachment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.UnaryOperator;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements ArmorReworkLivingEntityAccess {

	@Unique
	private BarrierAttachment getBarrierComponent() {
		return BarrierAttachment.get((PlayerEntity)(Object)this);
	}


	@Override
	public float getBarrier() {
		return getBarrierComponent().getValue();
	}

	@Override
	public void setBarrier(float barrier) {
		getBarrierComponent().setValue(barrier);
	}

	@Override
	public void modifyBarrier(UnaryOperator<Float> function) {
		getBarrierComponent().modifyValue(function);
	}

}
