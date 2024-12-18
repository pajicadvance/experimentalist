package me.pajic.experimentalist.mixin;

import me.pajic.experimentalist.config.ModConfig;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class ServerMainMixin {

    @Inject(
            method = "<clinit>",
            at = @At("HEAD")
    )
    private static void onInit(CallbackInfo ci) {
        ModConfig.loadConfig();
    }
}
