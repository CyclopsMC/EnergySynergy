package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.redstoneflux.api.IEnergyContainerItem;
import net.darkhax.tesla.api.ITeslaHolder;
import net.minecraft.item.ItemStack;

/**
 * Tesla holder wrapper for {@link IEnergyContainerItem}.
 * @author rubensworks
 */
public class HolderEnergyContainerItem implements ITeslaHolder {

    private final ItemStack itemStack;

    public HolderEnergyContainerItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected IEnergyContainerItem getItem() {
        return (IEnergyContainerItem) itemStack.getItem();
    }

    @Override
    public long getStoredPower() {
        return getItem().getEnergyStored(itemStack);
    }

    @Override
    public long getCapacity() {
        return getItem().getMaxEnergyStored(itemStack);
    }
}
