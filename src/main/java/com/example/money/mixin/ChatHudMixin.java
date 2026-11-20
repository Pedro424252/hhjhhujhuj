package com.example.money.mixin;

import com.example.money.Money;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
    // Sohbete dusen her mesaj buradan gecer: event yolunu kacirsa bile yakalanir.
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V",
            at = @At("HEAD"), cancellable = true, require = 0)
    private void money$hide(Text message, MessageSignatureData sig, MessageIndicator indicator, CallbackInfo ci) {
        if (!Money.handleMessage(message)) ci.cancel();
    }
}
