package com.sindercube.armor_rework.content.components;

import com.mojang.serialization.Codec;
import com.sindercube.armor_rework.registry.ArmorReworkComponents;
import net.minecraft.entity.LivingEntity;

import java.util.function.UnaryOperator;

public class BarrierAttachment {

	public static final Codec<BarrierAttachment> CODEC = Codec.FLOAT.xmap(
			BarrierAttachment::new, BarrierAttachment::getValue
	);



	private float value;

	public BarrierAttachment(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}


	public static BarrierAttachment get(LivingEntity entity) {
		return entity.getAttachedOrCreate(ArmorReworkComponents.BARRIER_ATTACHMENT, () -> new BarrierAttachment(0));
	}

}
