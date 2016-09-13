package org.cyclops.energysynergy.modcompat.ic2.capability.forgeenergy;

import ic2.core.block.TileEntityBlock;
import ic2.core.block.comp.Energy;
import net.minecraftforge.energy.IEnergyStorage;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Forge energy storage for {@link TileEntityBlock}.
 * @author rubensworks
 */
public class EnergyStorageConsumerBlock implements IEnergyStorage {

    private final TileEntityBlock host;

    public EnergyStorageConsumerBlock(TileEntityBlock host) {
        this.host = host;
    }

    protected Energy getEnergy() {
        return host.getComponent(Energy.class);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        Energy energy = getEnergy();
        if (energy != null) {
            double eu = Ic2Helpers.feToEu(maxReceive);
            double toGive = Math.min(eu, energy.getCapacity() - energy.getEnergy());
            if (!simulate) {
                return Ic2Helpers.euToFe(energy.addEnergy((int) toGive));

            }
            return Ic2Helpers.euToFe(toGive);
        }
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        Energy energy = getEnergy();
        if (energy != null) {
            double eu = Ic2Helpers.feToEu(maxExtract);
            return Ic2Helpers.euToFe(energy.useEnergy(eu, simulate));
        }
        return 0;
    }

    @Override
    public int getEnergyStored() {
        Energy energy = getEnergy();
        if (energy != null) {
            return Ic2Helpers.euToFe(energy.getEnergy());
        }
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        Energy energy = getEnergy();
        if (energy != null) {
            return Ic2Helpers.euToFe(energy.getCapacity());
        }
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
