package mod.shoulder_surfing.irons_spells_integration;

import com.github.exopandora.shouldersurfing.client.ShoulderSurfingImpl;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.player.ClientMagicData;

/**
 * <p>
 * Background:
 * The mods "Iron's Spells 'n Spellbooks" and "Shoulder Surfing Reloaded" are not fully compatible.
 * When a spell is cast using a spellbook (for example, Fire Bolt) while the player is in Shoulder
 * Surfing perspective, the spell will not aim at the crosshair.
 * However, when casting the spell using a scroll item directly (without the spellbook) and camera is decoupled,
 * it works as expected,since Shoulder Surfing is able to detect that.
 * <p>
 * The Shoulder Surfing mod has a feature to aim at target whenever an item is equipped
 * in the Curios slot. It can be configured as follows:
 * adaptive_crosshair_items = ["spellbook@irons_spellbooks:.*_book", "spellbook@*"]
 * However, this makes the player look at the crosshair at all times while the spellbook is equipped.
 * in the Curios slot. See: <a href="https://github.com/Exopandora/ShoulderSurfing/issues/328">...</a>
 * <p>
 * This mod tries to fix the issue by adjusting the spell aim toward the crosshair target
 * whenever a spell is cast using a spellbook, scroll item, quick cast.
 * <p>
 * For more info: <a href="https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration#echnical-implementation">README.</a>
 */
public class ShoulderSurfingIronsSpellsIntegrationClient {

    private static ShoulderSurfingImpl getShoulderSurfing() {
        return ShoulderSurfingImpl.getInstance();
    }

    private static void lookAtCrosshairTarget() {
        getShoulderSurfing().lookAtCrosshairTarget();
    }

    private static void lookAtCrosshairTargetIfShoulderSurfing() {
        if (getShoulderSurfing().isShoulderSurfing()) {
            lookAtCrosshairTarget();
        }
    }

    private static boolean isContinuousSpell(CastType castType) {
        return castType == CastType.CONTINUOUS;
    }

    private static boolean isCastingContinuousSpell() {
        return ClientMagicData.isCasting() && isContinuousSpell(ClientMagicData.getCastType());
    }

    public static void onCastSpellUsingSpellBook() {
        lookAtCrosshairTargetIfShoulderSurfing();
    }

    public static void onUseScrollItem() {
        lookAtCrosshairTargetIfShoulderSurfing();
    }

    public static boolean shouldAimAtTarget() {
        return isCastingContinuousSpell();
    }
}
