package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.api.energy.IEnergyContainerItem;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.item.ItemStack;

/**
 * Tesla producer wrapper for {@link IEnergyContainerItem}.
 * @author rubensworks
 */
public class ProducerEnergyContainerItem implements ITeslaProducer {

    private final ItemStack itemStack;

    public ProducerEnergyContainerItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    protected IEnergyContainerItem getItem() {
        return (IEnergyContainerItem) itemStack.getItem();
    }

    @Override
    public long takePower(long power, boolean simulated) {
        return getItem().extractEnergy(itemStack, (int) Math.min(Integer.MAX_VALUE, power), simulated);
    }
}
