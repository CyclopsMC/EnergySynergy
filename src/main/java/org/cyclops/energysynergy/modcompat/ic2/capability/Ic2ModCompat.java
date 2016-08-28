package org.cyclops.energysynergy.modcompat.ic2.capability;

import net.minecraftforge.fml.common.Loader;
import org.cyclops.cyclopscore.modcompat.IModCompat;
import org.cyclops.energysynergy.Reference;
import org.cyclops.energysynergy.modcompat.ic2.capability.tesla.Ic2TeslaIntegration;

/**
 * Capabilities for IC2.
 *
 * @author rubensworks
 */
public class Ic2ModCompat implements IModCompat {
    @Override
    public String getModID() {
        return Reference.MOD_IC2;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getComment() {
        return "Tesla capabilities for IC2 tiles and items.";
    }

    @Override
    public void onInit(Step initStep) {
        if (initStep == Step.PREINIT && Loader.isModLoaded(Reference.MOD_TESLA)) {
            Ic2TeslaIntegration.load();
        }
    }

}
