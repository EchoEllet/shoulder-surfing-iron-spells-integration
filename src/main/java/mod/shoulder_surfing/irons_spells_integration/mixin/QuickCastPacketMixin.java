package mod.shoulder_surfing.irons_spells_integration.mixin;

import io.redspace.ironsspellbooks.network.casting.QuickCastPacket;
import mod.shoulder_surfing.irons_spells_integration.ShoulderSurfingIronsSpellsIntegration;
import mod.shoulder_surfing.irons_spells_integration.ShoulderSurfingIronsSpellsIntegrationClient;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Similar to {@link CastPacketMixin} but for quick cast.
 * */
@OnlyIn(Dist.CLIENT)
@Mixin(QuickCastPacket.class)
public class QuickCastPacketMixin {
    /**
     * Assumes that {@link QuickCastPacketMixin} is instantiated when the player presses the key to cast a spell using a spellbook.
     * This behavior is accurate at the time of writing, and while future updates to Iron Spells may change it, such changes are unlikely.
     * */
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onCastSpellUsingSpellBook(CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player == null) {
            ShoulderSurfingIronsSpellsIntegration.LOGGER.error("The local Minecraft player is null on spell quick cast using a spell book. The functionality of this mod may not work!");
            return;
        }
        ShoulderSurfingIronsSpellsIntegrationClient.onCastSpellUsingSpellBook();
    }
}
