package org.cyclops.energysynergy.modcompat.ic2.capability.forgeenergy;

import ic2.api.item.IElectricItem;
import ic2.api.tile.IEnergyStorage;
import ic2.core.block.TileEntityBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import org.cyclops.cyclopscore.modcompat.capabilities.CapabilityConstructorRegistry;
import org.cyclops.cyclopscore.modcompat.capabilities.DefaultCapabilityProvider;
import org.cyclops.cyclopscore.modcompat.capabilities.ICapabilityConstructor;
import org.cyclops.cyclopscore.modcompat.capabilities.SimpleCapabilityConstructor;
import org.cyclops.energysynergy.EnergySynergy;

import javax.annotation.Nullable;

/**
 * Loader for IC2 Forge Energy capabilities.
 * @author rubensworks
 */
public class Ic2ForgeEnergyIntegration {

    public static void load() {
        CapabilityConstructorRegistry registry = EnergySynergy._instance.getCapabilityConstructorRegistry();

        // Tiles
        registry.registerInheritableTile(IEnergyStorage.class,
                new SimpleCapabilityConstructor<net.minecraftforge.energy.IEnergyStorage, IEnergyStorage>() {
                    @Override
                    public Capability<net.minecraftforge.energy.IEnergyStorage> getCapability() {
                        return CapabilityEnergy.ENERGY;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(IEnergyStorage host) {
                        return new DefaultCapabilityProvider<>(getCapability(),
                                new EnergyStorageEnergyStorage(host));
                    }
                });
        registry.registerInheritableTile(TileEntityBlock.class,
                new SimpleCapabilityConstructor<net.minecraftforge.energy.IEnergyStorage, TileEntityBlock>() {
                    @Override
                    public Capability<net.minecraftforge.energy.IEnergyStorage> getCapability() {
                        return CapabilityEnergy.ENERGY;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(TileEntityBlock host) {
                        if (!(host instanceof IEnergyStorage)) {
                            return new DefaultCapabilityProvider<>(getCapability(),
                                    new EnergyStorageConsumerBlock(host));
                        }
                        return null;
                    }
                });

        // Items
        registry.registerInheritableItem(IElectricItem.class,
                new ICapabilityConstructor<net.minecraftforge.energy.IEnergyStorage, IElectricItem, ItemStack>() {
                    @Override
                    public Capability<net.minecraftforge.energy.IEnergyStorage> getCapability() {
                        return CapabilityEnergy.ENERGY;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(IElectricItem hostType, final ItemStack host) {
                        return new DefaultCapabilityProvider<>(getCapability(),
                                new EnergyStorageElectricItem(hostType, host));
                    }
                });
    }

}
