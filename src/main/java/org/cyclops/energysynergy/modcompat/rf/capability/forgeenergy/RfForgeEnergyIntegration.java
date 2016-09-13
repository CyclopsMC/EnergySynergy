package org.cyclops.energysynergy.modcompat.rf.capability.forgeenergy;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.cyclops.cyclopscore.modcompat.capabilities.*;
import org.cyclops.energysynergy.EnergySynergy;

import javax.annotation.Nullable;

/**
 * Loader for RF Forge Energy capabilities.
 * @author rubensworks
 */
public class RfForgeEnergyIntegration {

    public static void load() {
        CapabilityConstructorRegistry registry = EnergySynergy._instance.getCapabilityConstructorRegistry();

        // Tiles
        registry.registerInheritableTile(IEnergyHandler.class,
                new SimpleCapabilityConstructor<IEnergyStorage, IEnergyHandler>() {
                    @Override
                    public Capability<IEnergyStorage> getCapability() {
                        return CapabilityEnergy.ENERGY;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(final IEnergyHandler host) {
                        final boolean isReceiver = host instanceof IEnergyReceiver;
                        final boolean isProvider = host instanceof IEnergyProvider;
                        return new DefaultSidedCapabilityProvider<>(DefaultSidedCapabilityProvider.forAllSides(
                                getCapability(),
                                new DefaultSidedCapabilityProvider.ISidedCapabilityConstructor<IEnergyStorage>() {
                                    @Override
                                    public IEnergyStorage createForSide(EnumFacing side) {
                                        return new EnergyStorageEnergyHandler(side, host,
                                                isReceiver ? (IEnergyReceiver) host : null,
                                                isProvider ? (IEnergyProvider) host : null);
                                    }
                                }));
                    }
                });

        // Items
        registry.registerInheritableItem(IEnergyContainerItem.class,
                new ICapabilityConstructor<IEnergyStorage, Item, ItemStack>() {
                    @Override
                    public Capability<IEnergyStorage> getCapability() {
                        return CapabilityEnergy.ENERGY;
                    }

                    @Nullable
                    @Override
                    public ICapabilityProvider createProvider(Item hostType, final ItemStack host) {
                        return new DefaultCapabilityProvider<>(getCapability(),
                                new EnergyStorageContainerItem(host));
                    }
                });
    }

}
