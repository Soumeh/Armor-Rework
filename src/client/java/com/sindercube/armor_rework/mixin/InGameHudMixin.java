package com.sindercube.armor_rework.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sindercube.armor_rework.ArmorType;
import com.sindercube.armor_rework.BarrierAttachment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Shadow @Final private static Identifier ARMOR_EMPTY_TEXTURE;
	@Shadow @Final private static Identifier ARMOR_HALF_TEXTURE;
	@Shadow @Final private static Identifier ARMOR_FULL_TEXTURE;








	@Inject(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Util;getMeasuringTimeMs()J"))
	private void getBlinkingValue(DrawContext context, CallbackInfo ci, @Local boolean bl, @Share("blinking") LocalBooleanRef blinking) {
		blinking.set(bl);
	}









	@Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void renderArmorAndBarrier(DrawContext context, PlayerEntity player, int height, int j, int k, int width, CallbackInfo ci, @Share("blinking") LocalBooleanRef blinking) {
		ci.cancel();

		ArmorType type = ArmorType.NORMAL;
		int barrier = BarrierAttachment.get(player).getBarrier();
		boolean half = barrier % 2 == 1;

		int armor = Math.min(player.getArmor()/2, 10)-1;
		barrier = Math.min(barrier/2, 10)-1;

		int y = height - (j - 1) * k - 10;
		RenderSystem.enableBlend();

		while (armor >= 0) {
			int x = width + armor * 8;
			type.renderEmpty(context, x, y);
			armor--;
		}

		while (barrier >= 0) {
			int x = width + barrier * 8;
			if (half) {
				type.renderHalf(context, x, y, blinking.get());
				half = false;
			} else {
				type.renderFull(context, x, y, blinking.get());
			}
			barrier--;
		}

		RenderSystem.disableBlend();
	}

}