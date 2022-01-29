package io.github.sadcreepy.cursedhud.mixin;

import net.minecraft.client.gui.InGame;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.sadcreepy.cursedhud.events.init.ConfigListener.cursedHudConfig;

@Mixin(InGame.class)
public class InGameMixin {

    @Inject(at = @At("TAIL"), method = "renderHud", remap = false)
    private void renderHud(float f, boolean flag, int i, int j, CallbackInfo ci) {

    }

    //Offset the hp bar to the center

    @ModifyArg(
            method = "renderHud",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 6),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 10)
            ),
            index = 0)
    private int injectedHp(int x) {
        if (PlayerHelper.getPlayerFromGame().inventory.getArmourValue() == 0) {
            return x + 50;
        }
        return x;
    }

    //Offset the air bar to the center

    @ModifyArg(
            method = "renderHud",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 11),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 12)
            ),
            index = 0)
    private int injectedAir(int x) {
        return x + 50;
    }

    //Hotbar Offset

    @ModifyArg(
            method = "renderHud",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 0),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 1)
            ),
            index = 1)
    private int injectedHotbar(int y) {
        return y - cursedHudConfig.hotbarOffset;
    }

    @ModifyArg(
            method = "renderHotbarSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/ItemRenderer;method_1487(Lnet/minecraft/client/render/TextRenderer;Lnet/minecraft/client/texture/TextureManager;Lnet/minecraft/item/ItemInstance;II)V"),
            index = 4)
    private int injectedItem(int j) {
        return j - cursedHudConfig.hotbarOffset;
    }

    @ModifyArg(
            method = "renderHotbarSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/ItemRenderer;method_1488(Lnet/minecraft/client/render/TextRenderer;Lnet/minecraft/client/texture/TextureManager;Lnet/minecraft/item/ItemInstance;II)V"),
            index = 4)
    private int injectedNumbers(int j) {
        return j - cursedHudConfig.hotbarOffset;
    }

    //Edit UV of selected slot

    @ModifyArg(
            method = "renderHud",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V",
                    ordinal = 1),
            index = 5)
    private int injectedSelectedSlot(int height) {
        return height + 2;
    }

    //Bars Offset

    @ModifyArg(
            method = "renderHud",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 3),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 10)
            ),
            index = 1)
    private int injectedBars(int y) {
        if (cursedHudConfig.topAlignment) {
            return cursedHudConfig.barsOffset;
        } else {
            return y - cursedHudConfig.hotbarOffset - cursedHudConfig.barsOffset;
        }
    }

    @ModifyArg(
            method = "renderHud",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 11),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/InGame;blit(IIIIII)V", ordinal = 12)
            ),
            index = 1)
    private int injectedAirY(int y) {
        if (cursedHudConfig.topAlignment) {
            return cursedHudConfig.barsOffset + cursedHudConfig.airbarOffset;
        } else {
            return y - cursedHudConfig.hotbarOffset - cursedHudConfig.barsOffset - cursedHudConfig.airbarOffset;
        }
    }

}