package org.cyclops.energysynergy.modcompat.rf.capability.forgeenergy;

import cofh.redstoneflux.api.IEnergyHandler;
import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

/**
 * Forge energy storage wrapper for {@link IEnergyStorage}.
 * @author rubensworks
 */
public class EnergyStorageEnergyHandler implements IEnergyStorage {

    private final EnumFacing side;
    private final IEnergyHandler energyHandler;
    // We store the following two to avoid having to cast, which has a cost.
    private final IEnergyReceiver energyReceiver;
    private final IEnergyProvider energyProvider;

    public EnergyStorageEnergyHandler(EnumFacing side, IEnergyHandler energyHandler,
                                      @Nullable IEnergyReceiver energyReceiver,
                                      @Nullable IEnergyProvider energyProvider) {
        this.side = side;
        this.energyHandler = energyHandler;
        this.energyReceiver = energyReceiver;
        this.energyProvider = energyProvider;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (energyReceiver == null) {
            return 0;
        }
        return energyReceiver.receiveEnergy(side, maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (energyProvider == null) {
            return 0;
        }
        return energyProvider.extractEnergy(side, maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return energyHandler.getEnergyStored(side);
    }

    @Override
    public int getMaxEnergyStored() {
        return energyHandler.getMaxEnergyStored(side);
    }

    @Override
    public boolean canExtract() {
        return energyProvider != null;
    }

    @Override
    public boolean canReceive() {
        return energyReceiver != null;
    }
}
