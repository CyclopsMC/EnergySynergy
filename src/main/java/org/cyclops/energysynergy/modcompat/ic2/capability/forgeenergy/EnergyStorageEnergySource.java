package org.cyclops.energysynergy.modcompat.ic2.capability.forgeenergy;

import ic2.api.energy.tile.IEnergySource;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Forge energy storage for {@link IEnergySource}.
 * @author rubensworks
 */
public class EnergyStorageEnergySource implements net.minecraftforge.energy.IEnergyStorage {

    private final IEnergySource host;

    public EnergyStorageEnergySource(IEnergySource host) {
        this.host = host;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        double eu = Ic2Helpers.feToEu(maxExtract);
        double toTake = Math.min(eu, host.getOfferedEnergy());
        if (!simulate) {
            host.drawEnergy(toTake);
        }
        return Ic2Helpers.euToFe(toTake);
    }

    @Override
    public int getEnergyStored() {
        return Ic2Helpers.euToFe(host.getOfferedEnergy());
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
