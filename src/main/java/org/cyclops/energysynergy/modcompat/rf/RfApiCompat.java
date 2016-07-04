package org.cyclops.energysynergy.modcompat.rf;

import net.minecraftforge.fml.common.ModAPIManager;
import org.cyclops.cyclopscore.modcompat.IApiCompat;
import org.cyclops.energysynergy.Reference;
import org.cyclops.energysynergy.modcompat.rf.capability.tesla.RfTeslaIntegration;

/**
 * Mod compat for the RF API.
 * @author rubensworks
 *
 */
public class RfApiCompat implements IApiCompat {

	@Override
	public void onInit(final Step initStep) {
		if(initStep == Step.PREINIT && ModAPIManager.INSTANCE.hasAPI(Reference.MOD_TESLA_API)) {
			RfTeslaIntegration.load();
		}
	}

	@Override
	public String getApiID() {
		return Reference.MOD_RF_API;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getComment() {
		return "Tesla capabilities for RF tiles and items.";
	}

}
