package com.sindercube.armor_rework;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorRework implements ModInitializer {

	public static final String MOD_ID = "armor_rework";
    public static final Logger LOGGER = LoggerFactory.getLogger("armor-rework");

	public static Identifier of(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}


	public static final AttachmentType<BarrierAttachment> BARRIER_ATTACHMENT = AttachmentRegistry.createPersistent(
			of("barrier"), BarrierAttachment.CODEC
	);

}