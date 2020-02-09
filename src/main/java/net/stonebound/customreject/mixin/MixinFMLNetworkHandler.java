package net.stonebound.customreject.mixin;

import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.stonebound.customreject.CustomReject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = FMLNetworkHandler.class, priority = 1001, remap = false)
public class MixinFMLNetworkHandler {
    @ModifyArg(method = "checkModList(Ljava/util/Map;Lnet/minecraftforge/fml/relauncher/Side;)Ljava/lang/String;",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"))
    private static String customRejectionMessage(String rejectString) {
        String rejectionMessage = CustomReject.ModConfig.rejectionMessage;
        if (CustomReject.ModConfig.includeMismatchedMods)
            rejectionMessage = rejectionMessage + "\n" + rejectString;
        return rejectionMessage;
    }

}

