package mod.shoulder_surfing.irons_spells_integration;

import com.github.exopandora.shouldersurfing.api.client.event.ComputePlayerAimStateEvent;
import com.github.exopandora.shouldersurfing.api.client.event.handler.ComputePlayerAimStateEventHandler;
import com.github.exopandora.shouldersurfing.api.event.IEventBus;
import com.github.exopandora.shouldersurfing.api.plugin.IShoulderSurfingPlugin;

@SuppressWarnings("unused") // Referenced in src/main/resources/shouldersurfing_plugin.json
public class ShoulderSurfingIronsSpellsPlugin implements IShoulderSurfingPlugin {
    @Override
    public void register(IEventBus eventBus) {
        eventBus.register(new ShouldAimAtTargetCallback());
    }

    private static class ShouldAimAtTargetCallback implements ComputePlayerAimStateEventHandler {
        @Override
        public void handle(ComputePlayerAimStateEvent event) {
            if (ShoulderSurfingIronsSpellsIntegrationClient.shouldAimAtTarget()) {
                event.setResult(true);
            }
        }
    }
}