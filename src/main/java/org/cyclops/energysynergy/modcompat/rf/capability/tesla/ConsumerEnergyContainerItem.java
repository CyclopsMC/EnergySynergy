package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.redstoneflux.api.IEnergyContainerItem;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.minecraft.item.ItemStack;

/**
 * Tesla consumer wrapper for {@link IEnergyContainerItem}.
 * @author rubensworks
 */
public class ConsumerEnergyContainerItem implements ITeslaConsumer {

    private final ItemStack itemStack;

    public ConsumerEnergyContainerItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected IEnergyContainerItem getItem() {
        return (IEnergyContainerItem) itemStack.getItem();
    }

    @Override
    public long givePower(long power, boolean simulated) {
        return getItem().receiveEnergy(itemStack, (int) Math.min(Integer.MAX_VALUE, power), simulated);
    }
}
