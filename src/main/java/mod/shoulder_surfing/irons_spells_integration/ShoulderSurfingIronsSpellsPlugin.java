package mod.shoulder_surfing.irons_spells_integration;

import com.github.exopandora.shouldersurfing.api.callback.ICameraCouplingCallback;
import com.github.exopandora.shouldersurfing.api.plugin.IShoulderSurfingPlugin;
import com.github.exopandora.shouldersurfing.api.plugin.IShoulderSurfingRegistrar;
import net.minecraft.client.Minecraft;

// Referenced in src/main/resources/shouldersurfing_plugin.json
@SuppressWarnings("unused")
public class ShoulderSurfingIronsSpellsPlugin implements IShoulderSurfingPlugin {
    @Override
    public void register(IShoulderSurfingRegistrar registrar) {
        registrar.registerCameraCouplingCallback(new CameraCouplingCallback());
    }

    private static class CameraCouplingCallback implements ICameraCouplingCallback {
        @Override
        public boolean isForcingCameraCoupling(Minecraft minecraft) {
            return ShoulderSurfingIronsSpellsIntegrationClient.shouldForceCameraCoupling();
        }
    }
}
