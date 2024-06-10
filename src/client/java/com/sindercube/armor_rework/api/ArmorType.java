package com.sindercube.armor_rework.api;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public record ArmorType (
		Identifier fullTexture,
		Identifier fullBlinkingTexture,
		Identifier halfTexture,
		Identifier halfBlinkingTexture,
		Identifier containerTexture,
		Identifier containerBlinkingTexture
) {

	public static final ArmorType NORMAL = new ArmorType(
			new Identifier("hud/armor/full"),
			new Identifier("hud/armor/full_blinking"),
			new Identifier("hud/armor/half"),
			new Identifier("hud/armor/half_blinking"),
			new Identifier("hud/armor/container"),
			new Identifier("hud/armor/container_blinking")
	);


	public void drawContainer(DrawContext context, int x, int y, boolean blinking) {
		var texture = containerTexture;
		if (blinking) texture = containerBlinkingTexture;
		context.drawGuiTexture(texture, x, y, 9, 9);
	}

	public void drawHalf(DrawContext context, int x, int y, boolean blinking) {
		var texture = halfTexture;
		if (blinking) texture = halfBlinkingTexture;
		context.drawGuiTexture(texture, x, y, 9, 9);
	}

	public void drawFull(DrawContext context, int x, int y, boolean blinking) {
		var texture = fullTexture;
		if (blinking) texture = fullBlinkingTexture;
		context.drawGuiTexture(texture, x, y, 9, 9);
	}

}
