package org.cyclops.energysynergy.modcompat.ic2.capability.forgeenergy;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.tile.IEnergyStorage;
import net.minecraft.util.EnumFacing;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Forge energy storage for {@link IEnergySink}.
 * @author rubensworks
 */
public class EnergyStorageEnergySink implements net.minecraftforge.energy.IEnergyStorage {

    private final EnumFacing side;
    private final IEnergySink host;

    public EnergyStorageEnergySink(EnumFacing side, IEnergySink host) {
        this.side = side;
        this.host = host;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        double eu = Ic2Helpers.feToEu(maxReceive);
        double toGive = Math.min(eu, host.getDemandedEnergy());
        if (!simulate) {
            return Ic2Helpers.euToFe(host.injectEnergy(side, toGive, host.getSinkTier()));

        }
        return Ic2Helpers.euToFe(toGive);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
