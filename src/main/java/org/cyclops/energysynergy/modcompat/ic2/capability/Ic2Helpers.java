package org.cyclops.energysynergy.modcompat.ic2.capability;

import org.cyclops.energysynergy.GeneralConfig;

/**
 * Helpers for IC2.
 * @author rubensworks
 */
public class Ic2Helpers {

    public static double teslaToEu(long tesla) {
        return tesla / GeneralConfig.euToTeslaRate;
    }

    public static long euToTesla(double eu) {
        return (long) Math.floor(eu * GeneralConfig.euToTeslaRate);
    }

    public static double feToEu(int fe) {
        return fe / GeneralConfig.euToTeslaRate;
    }

    public static int euToFe(double eu) {
        return (int) Math.floor(eu * GeneralConfig.euToTeslaRate);
    }

}
