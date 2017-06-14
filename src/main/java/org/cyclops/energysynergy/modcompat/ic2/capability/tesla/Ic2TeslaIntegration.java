package org.cyclops.energysynergy.modcompat.ic2.capability.tesla;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.IElectricItem;
import ic2.api.tile.IEnergyStorage;
import ic2.core.block.TileEntityBlock;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.apache.commons.lang3.tuple.Pair;
import org.cyclops.cyclopscore.datastructure.EnumFacingMap;
import org.cyclops.cyclopscore.modcompat.capabilities.*;
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
        registry.registerInheritableTile(IEnergySink.class,
                new SimpleCapabilityConstructor<ITeslaConsumer, IEnergySink>() {
                    @Override
                    public Capability<ITeslaConsumer> getCapability() {
                        return TeslaCapabilities.CAPABILITY_CONSUMER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(IEnergySink host) {
                        return new DefaultSidedCapabilityProvider<>(EnumFacingMap.forAllValues(
                                Pair.of(getCapability(), (ITeslaConsumer) new ConsumerEnergySink(EnumFacing.DOWN, host)),
                                Pair.of(getCapability(), (ITeslaConsumer) new ConsumerEnergySink(EnumFacing.UP, host)),
                                Pair.of(getCapability(), (ITeslaConsumer) new ConsumerEnergySink(EnumFacing.NORTH, host)),
                                Pair.of(getCapability(), (ITeslaConsumer) new ConsumerEnergySink(EnumFacing.SOUTH, host)),
                                Pair.of(getCapability(), (ITeslaConsumer) new ConsumerEnergySink(EnumFacing.WEST, host)),
                                Pair.of(getCapability(), (ITeslaConsumer) new ConsumerEnergySink(EnumFacing.EAST, host))
                        ));
                    }
                });
        registry.registerInheritableTile(IEnergyStorage.class,
                new SimpleCapabilityConstructor<ITeslaProducer, IEnergySource>() {
                    @Override
                    public Capability<ITeslaProducer> getCapability() {
                        return TeslaCapabilities.CAPABILITY_PRODUCER;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(IEnergySource host) {
                        return new DefaultCapabilityProvider<>(getCapability(), new ProducerEnergySource(host));
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
