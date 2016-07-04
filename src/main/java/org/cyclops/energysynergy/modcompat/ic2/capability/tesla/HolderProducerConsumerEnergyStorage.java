package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.api.tile.IEnergyStorage;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Tesla holder, producer and consumer for {@link IEnergyStorage}.
 * @author rubensworks
 */
public class HolderProducerConsumerEnergyStorage implements ITeslaHolder, ITeslaProducer, ITeslaConsumer {

    private final IEnergyStorage host;

    public HolderProducerConsumerEnergyStorage(IEnergyStorage host) {
        this.host = host;
    }

    @Override
    public long getStoredPower() {
        return Ic2Helpers.euToTesla(host.getStored());
    }

    @Override
    public long getCapacity() {
        return Ic2Helpers.euToTesla(host.getCapacity());
    }

    @Override
    public long givePower(long power, boolean simulated) {
        double eu = Ic2Helpers.teslaToEu(power);
        double toGive = Math.min(eu, host.getCapacity() - host.getStored());
        if (!simulated) {
            return Ic2Helpers.euToTesla(host.addEnergy((int) toGive));

        }
        return Ic2Helpers.euToTesla(toGive);
    }

    @Override
    public long takePower(long power, boolean simulated) {
        double eu = Ic2Helpers.teslaToEu(power);
        double toTake = Math.min(eu, host.getStored());
        if (!simulated) {
            return -Ic2Helpers.euToTesla(host.addEnergy((int) -toTake));
        }
        return Ic2Helpers.euToTesla(toTake);
    }
}
