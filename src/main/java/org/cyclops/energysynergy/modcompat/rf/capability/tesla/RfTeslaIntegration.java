package org.cyclops.energysynergy.modcompat.rf.capability.tesla;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.cyclops.cyclopscore.modcompat.capabilities.*;
import org.cyclops.energysynergy.EnergySynergy;

import javax.annotation.Nullable;

/**
 * Loader for RF Tesla capabilities.
 * @author rubensworks
 */
public class RfTeslaIntegration {

    public static void load() {
        CapabilityConstructorRegistry registry = EnergySynergy._instance.getCapabilityConstructorRegistry();

        // Tiles
        registry.registerInheritableTile(IEnergyHandler.class,
                new SimpleCapabilityConstructor<ITeslaHolder, IEnergyHandler>() {
                    @Override
                    public Capability<ITeslaHolder> getCapability() {
                        return TeslaCapabilities.CAPABILITY_HOLDER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(final IEnergyHandler host) {
                        return new DefaultSidedCapabilityProvider<>(DefaultSidedCapabilityProvider.forAllSides(
                                TeslaCapabilities.CAPABILITY_HOLDER,
                                new DefaultSidedCapabilityProvider.ISidedCapabilityConstructor<ITeslaHolder>() {
                                    @Override
                                    public ITeslaHolder createForSide(EnumFacing side) {
                                        return new HolderEnergyHandler(side, host);
                                    }
                                }));
                    }
                });
        registry.registerInheritableTile(IEnergyProvider.class,
                new SimpleCapabilityConstructor<ITeslaProducer, IEnergyProvider>() {
                    @Override
                    public Capability<ITeslaProducer> getCapability() {
                        return TeslaCapabilities.CAPABILITY_PRODUCER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(final IEnergyProvider host) {
                        return new DefaultSidedCapabilityProvider<>(DefaultSidedCapabilityProvider.forAllSides(
                                TeslaCapabilities.CAPABILITY_PRODUCER,
                                new DefaultSidedCapabilityProvider.ISidedCapabilityConstructor<ITeslaProducer>() {
                                    @Override
                                    public ITeslaProducer createForSide(EnumFacing side) {
                                        return new ProducerEnergyProvider(side, host);
                                    }
                                }));
                    }
                });
        registry.registerInheritableTile(IEnergyReceiver.class,
                new SimpleCapabilityConstructor<ITeslaConsumer, IEnergyReceiver>() {
                    @Override
                    public Capability<ITeslaConsumer> getCapability() {
                        return TeslaCapabilities.CAPABILITY_CONSUMER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(final IEnergyReceiver host) {
                        return new DefaultSidedCapabilityProvider<>(DefaultSidedCapabilityProvider.forAllSides(
                                TeslaCapabilities.CAPABILITY_CONSUMER,
                                new DefaultSidedCapabilityProvider.ISidedCapabilityConstructor<ITeslaConsumer>() {
                                    @Override
                                    public ITeslaConsumer createForSide(EnumFacing side) {
                                        return new ConsumerEnergyReceiver(side, host);
                                    }
                                }));
                    }
                });

        // Items
        registry.registerInheritableItem(IEnergyContainerItem.class,
                new ICapabilityConstructor<ITeslaHolder, Item, ItemStack>() {
                    @Override
                    public Capability<ITeslaHolder> getCapability() {
                        return TeslaCapabilities.CAPABILITY_HOLDER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(Item hostType, final ItemStack host) {
                        return new DefaultCapabilityProvider<>(TeslaCapabilities.CAPABILITY_HOLDER,
                                new HolderEnergyContainerItem(host));
                    }
                });
        registry.registerInheritableItem(IEnergyContainerItem.class,
                new ICapabilityConstructor<ITeslaProducer, Item, ItemStack>() {
                    @Override
                    public Capability<ITeslaProducer> getCapability() {
                        return TeslaCapabilities.CAPABILITY_PRODUCER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(Item hostType, final ItemStack host) {
                        return new DefaultCapabilityProvider<>(TeslaCapabilities.CAPABILITY_PRODUCER,
                                new ProducerEnergyContainerItem(host));
                    }
                });
        registry.registerInheritableItem(IEnergyContainerItem.class,
                new ICapabilityConstructor<ITeslaConsumer, Item, ItemStack>() {
                    @Override
                    public Capability<ITeslaConsumer> getCapability() {
                        return TeslaCapabilities.CAPABILITY_CONSUMER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(Item hostType, final ItemStack host) {
                        return new DefaultCapabilityProvider<>(TeslaCapabilities.CAPABILITY_CONSUMER,
                                new ConsumerEnergyContainerItem(host));
                    }
                });
    }

}
