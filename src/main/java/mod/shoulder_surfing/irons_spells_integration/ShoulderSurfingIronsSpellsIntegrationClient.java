package mod.shoulder_surfing.irons_spells_integration;

import com.github.exopandora.shouldersurfing.client.ShoulderSurfingImpl;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

/**
 * <p>
 * Background:
 * The mods "Iron's Spells 'n Spellbooks" and "Shoulder Surfing Reloaded" are not fully compatible.
 * When a spell is cast using a spellbook (for example, Fire Bolt) while the player is in Shoulder
 * Surfing perspective with camera decoupling enabled, the spell will not aim at the crosshair.
 * However, when casting the spell directly (without the spellbook), it works as expected,
 * since Shoulder Surfing detects the item usage through standard vanilla triggers (like throwing an egg).
 * <p>
 * The Shoulder Surfing mod has a feature to disable camera decoupling whenever an item is equipped
 * in the Curios slot. It can be configured as follows:
 * adaptive_crosshair_items = ["spellbook@irons_spellbooks:.*_book", "spellbook@*"]
 * However, this disables camera decoupling almost all the time while the spellbook is equipped
 * in the Curios slot. See: <a href="https://github.com/Exopandora/ShoulderSurfing/issues/328">...</a>
 * <p>
 * This mod tries to fix the issue by adjusting the spell aim toward the crosshair target
 * whenever a spell is cast using a spellbook while camera decoupling is enabled.
 */
@Mod(value = ShoulderSurfingIronsSpellsIntegration.MOD_ID, dist = Dist.CLIENT)
public class ShoulderSurfingIronsSpellsIntegrationClient {
    public ShoulderSurfingIronsSpellsIntegrationClient(ModContainer container) {}

    private static ShoulderSurfingImpl getShoulderSurfing() {
        return ShoulderSurfingImpl.getInstance();
    }

    private static void lookAtCrosshairTarget() {
        getShoulderSurfing().lookAtCrosshairTarget();
    }

    private static boolean isShoulderSurfingCameraDecoupled() {
        final ShoulderSurfingImpl shoulderSurfing = getShoulderSurfing();

        return shoulderSurfing.isShoulderSurfing() && shoulderSurfing.isCameraDecoupled();
    }

    private static boolean isContinuousSpell(CastType castType) {
        return castType == CastType.CONTINUOUS;
    }

    private static boolean isCastingContinuousSpell() {
        return ClientMagicData.isCasting() && isContinuousSpell(ClientMagicData.getCastType());
    }

    private static void lookAtCrosshairTargetIfCameraDecoupled() {
        if (isShoulderSurfingCameraDecoupled()) {
            lookAtCrosshairTarget();
        }
    }

    public static void onCastSpellUsingSpellBook() {
        lookAtCrosshairTargetIfCameraDecoupled();
    }

    public static void onUseScrollItem() {
        lookAtCrosshairTargetIfCameraDecoupled();
    }

    public static boolean shouldForceCameraCoupling() {
        return isCastingContinuousSpell();
    }
}
