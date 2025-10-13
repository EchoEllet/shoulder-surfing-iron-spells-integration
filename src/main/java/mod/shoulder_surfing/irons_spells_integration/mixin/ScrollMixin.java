package mod.shoulder_surfing.irons_spells_integration.mixin;

import io.redspace.ironsspellbooks.item.Scroll;
import mod.shoulder_surfing.irons_spells_integration.ShoulderSurfingIronsSpellsIntegrationClient;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(Scroll.class)
public class ScrollMixin {
    @Inject(
            method = "use",
            at = @At("HEAD")
    )
    private void onUse(Level level, Player player, @NotNull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (level.isClientSide()) {
            // Workaround for controller compatibility:
            // Normally, when a player uses Iron Spell scrolls via keyboard, Shoulder Surfing mod automatically
            // adjusts the camera to look at the crosshair target. When using a controller, the mod cannot detect
            // the input correctly, so it would skip this adjustment.
            // This call ensures that camera targeting works correctly for any input method.
            ShoulderSurfingIronsSpellsIntegrationClient.lookAtCrosshairTarget();
        }
    }
}
