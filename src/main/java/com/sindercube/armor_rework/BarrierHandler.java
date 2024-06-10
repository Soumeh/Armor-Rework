package com.sindercube.armor_rework;

import com.sindercube.armor_rework.content.components.BarrierAttachment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BarrierHandler {

	public static void regenerateBarrier(LivingEntity entity) {
		entity.setBarrier(entity.getArmor());
	}

	public static void cutToMax(LivingEntity entity) {
		entity.modifyBarrier(barrier -> Math.min(barrier, entity.getArmor()));
	}

	public static float takeBarrierDamageFirst(LivingEntity entity, float amount) {
		var barrier = BarrierAttachment.get(entity);
		float f = amount;
		amount -= barrier.getValue();
		barrier.modifyValue(value -> value - f);
		return 0;
	}

}
