package com.sindercube.armor_rework.registry;

import com.sindercube.armor_rework.ArmorRework;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ArmorReworkTags {

	public static void init() {}


	public static final TagKey<DamageType> BYPASSES_BARRIER = damageType("bypasses_barrier");


	private static <T> TagKey<T> of(RegistryKey<Registry<T>> key, String path) {
		return TagKey.of(key, ArmorRework.of(path));
	}

	private static TagKey<DamageType> damageType(String path) {
		return of(RegistryKeys.DAMAGE_TYPE, path);
	}

}
