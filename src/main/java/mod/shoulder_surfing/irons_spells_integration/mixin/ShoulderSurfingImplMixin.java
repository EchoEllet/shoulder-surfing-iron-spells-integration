package mod.shoulder_surfing.irons_spells_integration.mixin;

import com.github.exopandora.shouldersurfing.client.ShoulderSurfingImpl;
import mod.shoulder_surfing.irons_spells_integration.ShoulderSurfingIronsSpellsIntegrationClient;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(ShoulderSurfingImpl.class)
public class ShoulderSurfingImplMixin {
    @Inject(
            method = "shouldEntityAimAtTargetInternal",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onShouldEntityAimAtTargetInternal(LivingEntity cameraEntity, Minecraft minecraft, CallbackInfoReturnable<Boolean> cir) {
        if (ShoulderSurfingIronsSpellsIntegrationClient.shouldForceAimAtTarget()) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
