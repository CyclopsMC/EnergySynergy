package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.api.energy.IEnergyReceiver;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.minecraft.util.EnumFacing;

/**
 * Tesla consumer wrapper for {@link IEnergyReceiver}.
 * @author rubensworks
 */
public class ConsumerEnergyReceiver implements ITeslaConsumer {

    private final EnumFacing side;
    private final IEnergyReceiver energyReceiver;

    public ConsumerEnergyReceiver(EnumFacing side, IEnergyReceiver energyReceiver) {
        this.side = side;
        this.energyReceiver = energyReceiver;
    }

    @Override
    public long givePower(long power, boolean simulated) {
        return energyReceiver.receiveEnergy(side, (int) Math.min(Integer.MAX_VALUE, power), simulated);
    }
}
