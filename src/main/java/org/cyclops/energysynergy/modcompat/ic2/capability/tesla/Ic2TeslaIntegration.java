package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.api.item.IElectricItem;
import ic2.api.tile.IEnergyStorage;
import ic2.core.block.TileEntityBlock;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.cyclops.cyclopscore.modcompat.capabilities.CapabilityConstructorRegistry;
import org.cyclops.cyclopscore.modcompat.capabilities.ICapabilityConstructor;
import org.cyclops.cyclopscore.modcompat.capabilities.MultipleCapabilityProvider;
import org.cyclops.cyclopscore.modcompat.capabilities.SimpleCapabilityConstructor;
import org.cyclops.energysynergy.EnergySynergy;

import javax.annotation.Nullable;

/**
 * Loader for IC2 Tesla capabilities.
 * @author rubensworks
 */
public class Ic2TeslaIntegration {

    public static void load() {
        CapabilityConstructorRegistry registry = EnergySynergy._instance.getCapabilityConstructorRegistry();

        // Tiles
        registry.registerInheritableTile(IEnergyStorage.class,
                new SimpleCapabilityConstructor<ITeslaHolder, IEnergyStorage>() {
                    @Override
                    public Capability<ITeslaHolder> getCapability() {
                        return TeslaCapabilities.CAPABILITY_HOLDER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(IEnergyStorage host) {
                        HolderProducerConsumerEnergyStorage capabilityImplementation = new HolderProducerConsumerEnergyStorage(host);
                        return MultipleCapabilityProvider.of(
                                TeslaCapabilities.CAPABILITY_HOLDER, capabilityImplementation,
                                TeslaCapabilities.CAPABILITY_PRODUCER, capabilityImplementation,
                                TeslaCapabilities.CAPABILITY_CONSUMER, capabilityImplementation
                        );
                    }
                });
        registry.registerInheritableTile(TileEntityBlock.class,
                new SimpleCapabilityConstructor<ITeslaHolder, TileEntityBlock>() {
                    @Override
                    public Capability<ITeslaHolder> getCapability() {
                        return TeslaCapabilities.CAPABILITY_HOLDER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(TileEntityBlock host) {
                        if (!(host instanceof IEnergyStorage)) {
                            HolderProducerConsumerBlock capabilityImplementation = new HolderProducerConsumerBlock(host);
                            return MultipleCapabilityProvider.of(
                                    TeslaCapabilities.CAPABILITY_HOLDER, capabilityImplementation,
                                    TeslaCapabilities.CAPABILITY_PRODUCER, capabilityImplementation,
                                    TeslaCapabilities.CAPABILITY_CONSUMER, capabilityImplementation
                            );
                        }
                        return null;
                    }
                });

        // Items
        registry.registerInheritableItem(IElectricItem.class,
                new ICapabilityConstructor<ITeslaHolder, IElectricItem, ItemStack>() {
                    @Override
                    public Capability<ITeslaHolder> getCapability() {
                        return TeslaCapabilities.CAPABILITY_HOLDER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(IElectricItem hostType, final ItemStack host) {
                        HolderProducerConsumerElectricItem capabilityImplementation = new HolderProducerConsumerElectricItem(hostType, host);
                        return MultipleCapabilityProvider.of(
                                TeslaCapabilities.CAPABILITY_HOLDER, capabilityImplementation,
                                TeslaCapabilities.CAPABILITY_PRODUCER, capabilityImplementation,
                                TeslaCapabilities.CAPABILITY_CONSUMER, capabilityImplementation
                        );
                    }
                });
    }

}
