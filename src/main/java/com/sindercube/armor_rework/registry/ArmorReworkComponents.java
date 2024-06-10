package com.sindercube.armor_rework.registry;

import com.mojang.serialization.Codec;
import com.sindercube.armor_rework.ArmorRework;
import com.sindercube.armor_rework.content.components.BarrierAttachment;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class ArmorReworkComponents {

	public static void init() {}


	public static final AttachmentType<BarrierAttachment> BARRIER_ATTACHMENT = register(
			"barrier", BarrierAttachment.CODEC
	);


	private static <T> AttachmentType<T> register(String path, Codec<T> codec) {
		return AttachmentRegistry.createPersistent(ArmorRework.of(path), codec);
	}

}
