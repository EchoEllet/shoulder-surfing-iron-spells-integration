package mod.shoulder_surfing.irons_spells_integration;

import org.apache.logging.log4j.LogManager;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import org.apache.logging.log4j.Logger;

@Mod(value = ShoulderSurfingIronsSpellsIntegration.MOD_ID)
public class ShoulderSurfingIronsSpellsIntegration {
    public static final String MOD_ID = "shoulder_surfing_irons_spells_integration";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public ShoulderSurfingIronsSpellsIntegration(IEventBus modEventBus, ModContainer modContainer) {}
}
