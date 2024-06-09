package com.sindercube.armor_rework;

import com.mojang.serialization.Codec;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.UnaryOperator;

public class BarrierAttachment {

	public static final Codec<BarrierAttachment> CODEC = Codec.INT.xmap(
			BarrierAttachment::new, BarrierAttachment::getBarrier
	);



	public int value;

	public BarrierAttachment(int value) {
		this.value = value;
	}

	public int getBarrier() {
		return value;
	}
	public void setBarrier(int value) {
		this.value = value;
	}
	public void modifyBarrier(UnaryOperator<Integer> function) {
		this.value = function.apply(value);
	}


	public void regenerate(PlayerEntity player) {
		var value = player.getArmor();
		setBarrier(value);
	}


	public static BarrierAttachment get(PlayerEntity player) {
		return player.getAttachedOrCreate(ArmorRework.BARRIER_ATTACHMENT, () -> new BarrierAttachment(0));
	}

}
