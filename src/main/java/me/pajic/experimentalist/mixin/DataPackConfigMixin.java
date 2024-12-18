package me.pajic.experimentalist.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.experimentalist.util.ModUtil;
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
    private static <E> ImmutableList<E> enableFeaturesByDefault(ImmutableList<E> original) {
        return (ImmutableList<E>) ModUtil.getGlobalFeatures();
    }
}
