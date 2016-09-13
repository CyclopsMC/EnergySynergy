package org.cyclops.energysynergy.modcompat.rf;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * A simple test mod which will test tesla tiles.
 * @author rubensworks
 */
@Mod(modid="test.energysynergy.rf.capability.tile",version="1.0")
public class TestCapabilityTileMod {
    
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent evt) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInteractHolder(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getItemStack() == null) return;
        if (event.getItemStack().getItem() != Items.BAKED_POTATO) return;

        TileEntity te = event.getWorld().getTileEntity(event.getPos());
        if (te != null) {
            if (event.getEntityPlayer().isSneaking() && te.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, event.getFace())) {
                event.setCanceled(true);
                ITeslaHolder holder = te.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, event.getFace());
                System.out.println("---Tesla---");
                System.out.println("Stored: " + holder.getStoredPower());
                System.out.println("Capacity: " + holder.getCapacity());
            } else if (te.hasCapability(CapabilityEnergy.ENERGY, event.getFace())) {
                event.setCanceled(true);
                IEnergyStorage holder = te.getCapability(CapabilityEnergy.ENERGY, event.getFace());
                System.out.println("---Forge Energy---");
                System.out.println("Stored: " + holder.getEnergyStored());
                System.out.println("Capacity: " + holder.getMaxEnergyStored());
            }
        }
    }

    @SubscribeEvent
    public void onInteractProducer(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getItemStack() == null) return;
        if (event.getItemStack().getItem() != Items.BOWL) return;

        TileEntity te = event.getWorld().getTileEntity(event.getPos());
        if (te != null) {
            if (event.getEntityPlayer().isSneaking() && te.hasCapability(TeslaCapabilities.CAPABILITY_PRODUCER, event.getFace())) {
                event.setCanceled(true);
                ITeslaProducer producer = te.getCapability(TeslaCapabilities.CAPABILITY_PRODUCER, event.getFace());
                System.out.println("---Tesla---");
                System.out.println("Take 100: " + producer.takePower(1000, false));
            } else if (te.hasCapability(CapabilityEnergy.ENERGY, event.getFace())) {
                event.setCanceled(true);
                IEnergyStorage holder = te.getCapability(CapabilityEnergy.ENERGY, event.getFace());
                System.out.println("---Forge Energy---");
                System.out.println("Take 100: " + holder.extractEnergy(1000, false));
            }
        }
    }

    @SubscribeEvent
    public void onInteractConsumer(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getItemStack() == null) return;
        if (event.getItemStack().getItem() != Items.APPLE) return;

        TileEntity te = event.getWorld().getTileEntity(event.getPos());
        if (te != null) {
            if (event.getEntityPlayer().isSneaking() && te.hasCapability(TeslaCapabilities.CAPABILITY_PRODUCER, event.getFace())) {
                event.setCanceled(true);
                ITeslaConsumer consumer = te.getCapability(TeslaCapabilities.CAPABILITY_CONSUMER, event.getFace());
                System.out.println("---Tesla---");
                System.out.println("Give 100: " + consumer.givePower(1000, false));
            } else if (te.hasCapability(CapabilityEnergy.ENERGY, event.getFace())) {
                event.setCanceled(true);
                IEnergyStorage holder = te.getCapability(CapabilityEnergy.ENERGY, event.getFace());
                System.out.println("---Forge Energy---");
                System.out.println("Take 100: " + holder.receiveEnergy(1000, false));
            }
        }
    }
}