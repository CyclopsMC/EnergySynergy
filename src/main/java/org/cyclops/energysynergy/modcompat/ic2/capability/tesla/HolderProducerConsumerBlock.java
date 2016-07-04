package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Tesla holder, producer and consumer for {@link TileEntityBlock}.
 * @author rubensworks
 */
public class HolderProducerConsumerBlock implements ITeslaHolder, ITeslaProducer, ITeslaConsumer {

    private final TileEntityBlock host;

    public HolderProducerConsumerBlock(TileEntityBlock host) {
        this.host = host;
    }

    protected Energy getEnergy() {
        return host.getComponent(Energy.class);
    }

    @Override
    public long getStoredPower() {
        Energy energy = getEnergy();
        if (energy != null) {
            return Ic2Helpers.euToTesla(energy.getEnergy());
        }
        return 0;
    }

    @Override
    public long getCapacity() {
        Energy energy = getEnergy();
        if (energy != null) {
            return Ic2Helpers.euToTesla(energy.getCapacity());
        }
        return 0;
    }

    @Override
    public long givePower(long power, boolean simulated) {
        Energy energy = getEnergy();
        if (energy != null) {
            double eu = Ic2Helpers.teslaToEu(power);
            double toGive = Math.min(eu, energy.getCapacity() - energy.getEnergy());
            if (!simulated) {
                return Ic2Helpers.euToTesla(energy.addEnergy((int) toGive));

            }
            return Ic2Helpers.euToTesla(toGive);
        }
        return 0;
    }

    @Override
    public long takePower(long power, boolean simulated) {
        Energy energy = getEnergy();
        if (energy != null) {
            double eu = Ic2Helpers.teslaToEu(power);
            return Ic2Helpers.euToTesla(energy.useEnergy(eu, simulated));
        }
        return 0;
    }
}
