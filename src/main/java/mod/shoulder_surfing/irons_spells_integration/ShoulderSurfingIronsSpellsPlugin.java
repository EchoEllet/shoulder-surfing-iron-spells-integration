package mod.shoulder_surfing.irons_spells_integration;

import com.github.exopandora.shouldersurfing.api.callback.ICameraCouplingCallback;
import com.github.exopandora.shouldersurfing.api.plugin.IShoulderSurfingPlugin;
import com.github.exopandora.shouldersurfing.api.plugin.IShoulderSurfingRegistrar;
import net.minecraft.client.Minecraft;

@SuppressWarnings("unused") // Referenced in src/main/resources/shouldersurfing_plugin.json
public class ShoulderSurfingIronsSpellsPlugin implements IShoulderSurfingPlugin {
    @Override
    public void register(IShoulderSurfingRegistrar registrar) {
        // Although the name IAdaptiveItemCallback is a bit misleading, it does exactly what we need:
        // makes the entity aim at the crosshair target.
        registrar.registerCameraCouplingCallback(new ShouldAimAtTargetCallback());
    }

    // TODO: Migrate to the new callback if/when this PR merged: https://github.com/Exopandora/ShoulderSurfing/pull/346
    //  See also: https://github.com/Exopandora/ShoulderSurfing/issues/345#issuecomment-3412713167
    private static class ShouldAimAtTargetCallback implements ICameraCouplingCallback {

        @Override
        public boolean isForcingCameraCoupling(Minecraft minecraft) {
            return ShoulderSurfingIronsSpellsIntegrationClient.shouldAimAtTarget();
        }
    }
}
