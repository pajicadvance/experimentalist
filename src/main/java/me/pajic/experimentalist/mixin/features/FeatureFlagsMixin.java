package me.pajic.experimentalist.mixin.features;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.experimentalist.Experimentalist;
import me.pajic.experimentalist.ModConfig;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagRegistry;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(FeatureFlags.class)
public class FeatureFlagsMixin {

    @Shadow @Final public static FeatureFlag VANILLA;
    @Shadow @Final public static FeatureFlagRegistry REGISTRY;

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/flag/FeatureFlagSet;of(Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/flag/FeatureFlagSet;"
            )
    )
    private static FeatureFlagSet enableFeaturesByDefault(FeatureFlagSet original) {
        ModConfig.initializeConfig(REGISTRY);
        FeatureFlagSet ffs = FeatureFlagSet.of(VANILLA);
        List<Identifier> names = new ArrayList<>();
        ModConfig.FEATURES.forEach((s, bl) -> {
            if (bl) names.add(Identifier.withDefaultNamespace(s));
        });
        return ffs.join(REGISTRY.fromNames(names));
    }
}
