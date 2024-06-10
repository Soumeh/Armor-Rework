package com.sindercube.armor_rework.asm;

import java.util.function.UnaryOperator;

public interface ArmorReworkLivingEntityAccess {

	default float getBarrier() {
		return 0;
	}
	default void setBarrier(float barrier) {}
	default void modifyBarrier(UnaryOperator<Float> function) {}

}
