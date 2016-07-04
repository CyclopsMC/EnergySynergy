package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.api.energy.IEnergyProvider;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.util.EnumFacing;

/**
 * Tesla producer wrapper for {@link IEnergyProvider}.
 * @author rubensworks
 */
public class ProducerEnergyProvider implements ITeslaProducer {

    private final EnumFacing side;
    private final IEnergyProvider energyProvider;

    public ProducerEnergyProvider(EnumFacing side, IEnergyProvider energyProvider) {
        this.side = side;
        this.energyProvider = energyProvider;
    }

    @Override
    public long takePower(long power, boolean simulated) {
        return energyProvider.extractEnergy(side, (int) Math.min(Integer.MAX_VALUE, power), simulated);
    }
}
