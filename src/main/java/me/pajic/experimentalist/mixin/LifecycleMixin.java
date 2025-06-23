package me.pajic.experimentalist.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Lifecycle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Lifecycle.class, remap = false)
public class LifecycleMixin {

    @Shadow @Final private static Lifecycle STABLE;

    @WrapMethod(method = "experimental")
    private static Lifecycle disableExperimentalWarnings(Operation<Lifecycle> original) {
        return STABLE;
    }
}
