package mod.shoulder_surfing.irons_spells_integration.mixin;

import io.redspace.ironsspellbooks.item.Scroll;
import mod.shoulder_surfing.irons_spells_integration.ShoulderSurfingIronsSpellsIntegrationClient;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
            // Safeguard
            return;
        }
        ShoulderSurfingIronsSpellsIntegrationClient.onUseScrollItem();
    }
}
