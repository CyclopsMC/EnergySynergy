package org.cyclops.energysynergy.modcompat.rf.capability.forgeenergy;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Forge energy storage wrapper for {@link IEnergyContainerItem}.
 * @author rubensworks
 */
public class EnergyStorageContainerItem implements IEnergyStorage {

    private final ItemStack itemStack;

    public EnergyStorageContainerItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected IEnergyContainerItem getItem() {
        return (IEnergyContainerItem) itemStack.getItem();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return getItem().receiveEnergy(itemStack, maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return getItem().extractEnergy(itemStack, maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return getItem().getEnergyStored(itemStack);
    }

    @Override
    public int getMaxEnergyStored() {
        return getItem().getMaxEnergyStored(itemStack);
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
