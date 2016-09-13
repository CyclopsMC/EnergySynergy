package org.cyclops.energysynergy.modcompat.ic2.capability.forgeenergy;

import ic2.api.tile.IEnergyStorage;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Forge energy storage for {@link IEnergyStorage}.
 * @author rubensworks
 */
public class EnergyStorageEnergyStorage implements net.minecraftforge.energy.IEnergyStorage {

    private final IEnergyStorage host;

    public EnergyStorageEnergyStorage(IEnergyStorage host) {
        this.host = host;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        double eu = Ic2Helpers.feToEu(maxReceive);
        double toGive = Math.min(eu, host.getCapacity() - host.getStored());
        if (!simulate) {
            return Ic2Helpers.euToFe(host.addEnergy((int) toGive));

        }
        return Ic2Helpers.euToFe(toGive);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        double eu = Ic2Helpers.feToEu(maxExtract);
        double toTake = Math.min(eu, host.getStored());
        if (!simulate) {
            return -Ic2Helpers.euToFe(host.addEnergy((int) -toTake));
        }
        return Ic2Helpers.euToFe(toTake);
    }

    @Override
    public int getEnergyStored() {
        return Ic2Helpers.euToFe(host.getStored());
    }

    @Override
    public int getMaxEnergyStored() {
        return Ic2Helpers.euToFe(host.getCapacity());
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
