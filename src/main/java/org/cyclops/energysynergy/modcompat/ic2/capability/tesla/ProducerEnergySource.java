package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.api.energy.tile.IEnergySource;
import net.darkhax.tesla.api.ITeslaProducer;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Tesla holder, producer and consumer for {@link IEnergySource}.
 * @author rubensworks
 */
public class ProducerEnergySource implements ITeslaProducer {

    private final IEnergySource host;

    public ProducerEnergySource(IEnergySource host) {
        this.host = host;
    }

    @Override
    public long takePower(long power, boolean simulated) {
        double eu = Ic2Helpers.teslaToEu(power);
        double toTake = Math.min(eu, host.getOfferedEnergy());
        if (!simulated) {
            host.drawEnergy(toTake);
            return Ic2Helpers.euToTesla(toTake);
        }
        return Ic2Helpers.euToTesla(toTake);
    }
}
