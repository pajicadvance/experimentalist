package me.pajic.experimentalist.mixin.features;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.experimentalist.ModConfig;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.ServerPacksSource;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPacksSource.class)
public class ServerPacksSourceMixin {

    @ModifyExpressionValue(
			method = "createBuiltInPackLocation",
            at = @At(
					value = "FIELD",
					target = "Lnet/minecraft/server/packs/repository/PackSource;FEATURE:Lnet/minecraft/server/packs/repository/PackSource;",
					opcode = Opcodes.GETSTATIC
			)
    )
    private static PackSource modifyPackSourceType(PackSource original, @Local(argsOnly = true) String id) {
		return ModConfig.FEATURES.get(id) ? PackSource.BUILT_IN : original;
    }
}
