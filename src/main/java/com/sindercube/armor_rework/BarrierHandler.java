package com.sindercube.armor_rework;

import com.sindercube.armor_rework.registry.ArmorReworkTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class BarrierHandler {

	public static void regenerateBarrier(LivingEntity entity) {
		entity.setBarrier(entity.getArmor());
	}

	public static void cutToMax(LivingEntity entity) {
		entity.modifyBarrier(barrier -> Math.min(barrier, entity.getArmor()));
	}

	public static float takeBarrierDamageFirst(LivingEntity entity, DamageSource source, float damage) {
		if (source.isIn(ArmorReworkTags.BYPASSES_BARRIER)) return damage;
		float originalDamage = damage;
		damage -= entity.getBarrier();
		entity.modifyBarrier(value -> value - originalDamage);
		return damage;
	}

}
