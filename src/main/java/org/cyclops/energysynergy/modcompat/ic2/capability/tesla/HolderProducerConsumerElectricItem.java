package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.item.ItemStack;
import org.cyclops.energysynergy.modcompat.ic2.capability.Ic2Helpers;

/**
 * Tesla holder, producer and consumer for {@link IElectricItem}.
 * @author rubensworks
 */
public class HolderProducerConsumerElectricItem implements ITeslaHolder, ITeslaProducer, ITeslaConsumer {

    private final IElectricItem hostType;
    private final ItemStack host;

    public HolderProducerConsumerElectricItem(IElectricItem hostType, ItemStack host) {
        this.hostType = hostType;
        this.host = host;
    }

    @Override
    public long getStoredPower() {
        return Ic2Helpers.euToTesla(ElectricItem.manager.getCharge(host));
    }

    @Override
    public long getCapacity() {
        return Ic2Helpers.euToTesla(hostType.getMaxCharge(host));
    }

    @Override
    public long givePower(long power, boolean simulated) {
        double eu = Ic2Helpers.teslaToEu(power);
        double charged = ElectricItem.manager.charge(host, eu, hostType.getTier(host), false, simulated);
        return Ic2Helpers.euToTesla(charged);
    }

    @Override
    public long takePower(long power, boolean simulated) {
        double eu = Ic2Helpers.teslaToEu(power);
        double charged = ElectricItem.manager.discharge(host, eu, hostType.getTier(host), false, false, simulated);
        return Ic2Helpers.euToTesla(charged);
    }
}
