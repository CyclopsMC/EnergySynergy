package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.tile.IEnergyStorage;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.minecraft.util.EnumFacing;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Tesla holder, producer and consumer for {@link IEnergyStorage}.
 * @author rubensworks
 */
public class ConsumerEnergySink implements ITeslaConsumer {

    private final EnumFacing side;
    private final IEnergySink host;

    public ConsumerEnergySink(EnumFacing side, IEnergySink host) {
        this.side = side;
        this.host = host;
    }

    @Override
    public long givePower(long power, boolean simulated) {
        double eu = Ic2Helpers.teslaToEu(power);
        double toGive = Math.min(eu, host.getDemandedEnergy());
        if (!simulated) {
            return Ic2Helpers.euToTesla(host.injectEnergy(side, toGive, host.getSinkTier()));

        }
        return Ic2Helpers.euToTesla(toGive);
    }
}
