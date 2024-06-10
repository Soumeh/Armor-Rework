package com.sindercube.armor_rework;

import com.sindercube.armor_rework.registry.ArmorReworkRegistries;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorRework implements ModInitializer {

	public static final String MOD_ID = "armor_rework";
    public static final Logger LOGGER = LoggerFactory.getLogger("Armor Rework");

	public static Identifier of(String path) {
		return Identifier.of(MOD_ID, path);
	}


	@Override
	public void onInitialize() {
		ArmorReworkRegistries.init();
		LOGGER.info("Hello Fabric world!");
	}

}