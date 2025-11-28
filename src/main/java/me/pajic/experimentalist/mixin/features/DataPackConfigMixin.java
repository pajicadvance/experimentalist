package me.pajic.experimentalist.mixin.features;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.experimentalist.ModConfig;
import net.minecraft.world.level.DataPackConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DataPackConfig.class)
public class DataPackConfigMixin {

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"
            )
    )
    private static ImmutableList<String> enableFeaturesByDefault(ImmutableList<String> original) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        builder.add("vanilla");
        ModConfig.FEATURES.forEach((s, bl) -> { if (bl) builder.add(s); });
        return builder.build();
    }
}