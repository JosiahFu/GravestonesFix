package archives.tater.gravestonesfix;

import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.event.TrinketDropCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.guavy.gravestones.config.GravestonesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GravestonesFix implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("gravestonesfix");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		if (FabricLoader.getInstance().isModLoaded("trinkets")) {
			TrinketDropCallback.EVENT.register((rule, stack, ref, entity) -> {
				if (GravestonesConfig.getConfig().mainSettings.enableGraves) return TrinketEnums.DropRule.KEEP;
				return rule;
			});
		}
	}
}
