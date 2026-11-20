package com.example.money.mixin;

import com.example.money.Money;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class SoundMixin {
    // Izinli oyuncunun tpa istegi geldikten sonra 2 sn icinde gelen sunucu seslerini susturur.
    @Inject(method = "onPlaySound(Lnet/minecraft/network/packet/s2c/play/PlaySoundS2CPacket;)V",
            at = @At("HEAD"), cancellable = true, require = 0)
    private void money$mute(PlaySoundS2CPacket packet, CallbackInfo ci) {
        if (System.currentTimeMillis() < Money.muteUntil) ci.cancel();
    }

    @Inject(method = "onPlaySoundFromEntity(Lnet/minecraft/network/packet/s2c/play/PlaySoundFromEntityS2CPacket;)V",
            at = @At("HEAD"), cancellable = true, require = 0)
    private void money$muteEntity(PlaySoundFromEntityS2CPacket packet, CallbackInfo ci) {
        if (System.currentTimeMillis() < Money.muteUntil) ci.cancel();
    }
}
