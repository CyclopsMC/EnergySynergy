package org.cyclops.energysynergy.modcompat.ic2.capability.forgeenergy;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.IEnergyStorage;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Forge energy storage for {@link IElectricItem}.
 * @author rubensworks
 */
public class EnergyStorageElectricItem implements IEnergyStorage {

    private final IElectricItem hostType;
    private final ItemStack host;

    public EnergyStorageElectricItem(IElectricItem hostType, ItemStack host) {
        this.hostType = hostType;
        this.host = host;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        double eu = Ic2Helpers.feToEu(maxReceive);
        double charged = ElectricItem.manager.charge(host, eu, hostType.getTier(host), false, simulate);
        return Ic2Helpers.euToFe(charged);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        double eu = Ic2Helpers.feToEu(maxExtract);
        double charged = ElectricItem.manager.discharge(host, eu, hostType.getTier(host), false, false, simulate);
        return Ic2Helpers.euToFe(charged);
    }

    @Override
    public int getEnergyStored() {
        return Ic2Helpers.euToFe(ElectricItem.manager.getCharge(host));
    }

    @Override
    public int getMaxEnergyStored() {
        return Ic2Helpers.euToFe(hostType.getMaxCharge(host));
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
