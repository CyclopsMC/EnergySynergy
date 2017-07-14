package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.redstoneflux.api.IEnergyHandler;
import net.darkhax.tesla.api.ITeslaHolder;
import net.minecraft.util.EnumFacing;

/**
 * Tesla holder wrapper for {@link IEnergyStorage}.
 * @author rubensworks
 */
public class HolderEnergyHandler implements ITeslaHolder {

    private final EnumFacing side;
    private final IEnergyHandler energyHandler;

    public HolderEnergyHandler(EnumFacing side, IEnergyHandler energyHandler) {
        this.side = side;
        this.energyHandler = energyHandler;
    }

    @Override
    public long getStoredPower() {
        return energyHandler.getEnergyStored(side);
    }

    @Override
    public long getCapacity() {
        return energyHandler.getMaxEnergyStored(side);
    }
}
