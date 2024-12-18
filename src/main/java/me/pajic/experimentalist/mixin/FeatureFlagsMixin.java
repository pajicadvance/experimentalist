package me.pajic.experimentalist.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.experimentalist.config.ModConfig;
import me.pajic.experimentalist.util.ModUtil;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FeatureFlags.class)
public class FeatureFlagsMixin {

    @Shadow @Final public static FeatureFlag VANILLA;
    @Shadow @Final public static FeatureFlag TRADE_REBALANCE;
    //? if <= 1.21.1
    /*@Shadow @Final public static FeatureFlag BUNDLE;*/
    //? if > 1.21.1 {
    @Shadow @Final public static FeatureFlag REDSTONE_EXPERIMENTS;
    @Shadow @Final public static FeatureFlag MINECART_IMPROVEMENTS;
    //?}
    //? if > 1.21.1 < 1.21.4
    /*@Shadow @Final public static FeatureFlag WINTER_DROP;*/

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/flag/FeatureFlagSet;of(Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/flag/FeatureFlagSet;"
            )
    )
    private static FeatureFlagSet enableFeaturesByDefault(FeatureFlagSet original) {
        FeatureFlagSet ffs = FeatureFlagSet.of(VANILLA);
        if (ModConfig.CONFIG.tradeRebalance) {
            ffs = ffs.join(FeatureFlagSet.of(TRADE_REBALANCE));
            ModUtil.addGlobalFeature("trade_rebalance");
        }
        //? if <= 1.21.1 {
        /*if (ModConfig.CONFIG.bundles) {
            ffs = ffs.join(FeatureFlagSet.of(BUNDLE));
            ModUtil.addGlobalFeature("bundle");
        }
        *///?}
        //? if > 1.21.1 {
        if (ModConfig.CONFIG.redstoneExperiments) {
            ffs = ffs.join(FeatureFlagSet.of(REDSTONE_EXPERIMENTS));
            ModUtil.addGlobalFeature("redstone_experiments");
        }
        if (ModConfig.CONFIG.minecartImprovements) {
            ffs = ffs.join(FeatureFlagSet.of(MINECART_IMPROVEMENTS));
            ModUtil.addGlobalFeature("minecart_improvements");
        }
        //?}
        //? if > 1.21.1 < 1.21.4 {
        /*if (ModConfig.CONFIG.winterDrop) {
            ffs = ffs.join(FeatureFlagSet.of(WINTER_DROP));
            ModUtil.addGlobalFeature("winter_drop");
        }
        *///?}
        return ffs;
    }
}
