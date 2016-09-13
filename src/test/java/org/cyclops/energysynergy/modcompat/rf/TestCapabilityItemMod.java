package org.cyclops.energysynergy.modcompat.rf;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * A simple test mod which will test tesla items.
 * @author rubensworks
 */
@Mod(modid="test.energysynergy.rf.capability.item",version="1.0")
public class TestCapabilityItemMod {
    
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent evt) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInteractHolder(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() != Blocks.STONE) return;

        ItemStack itemStack = event.getItemStack();
        if (itemStack != null) {
            if (event.getEntityPlayer().isSneaking() && itemStack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, null)) {
                event.setCanceled(true);
                ITeslaHolder holder = itemStack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, null);
                System.out.println("---Tesla---");
                System.out.println("Stored: " + holder.getStoredPower());
                System.out.println("Capacity: " + holder.getCapacity());
            } else if (itemStack.hasCapability(CapabilityEnergy.ENERGY, null)) {
                event.setCanceled(true);
                IEnergyStorage holder = itemStack.getCapability(CapabilityEnergy.ENERGY, null);
                System.out.println("---Forge Energy---");
                System.out.println("Stored: " + holder.getEnergyStored());
                System.out.println("Capacity: " + holder.getMaxEnergyStored());
            }
        }
    }

    @SubscribeEvent
    public void onInteractProducer(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() != Blocks.OBSIDIAN) return;

        ItemStack itemStack = event.getItemStack();
        if (itemStack != null) {
            if (event.getEntityPlayer().isSneaking() && itemStack.hasCapability(TeslaCapabilities.CAPABILITY_PRODUCER, null)) {
                event.setCanceled(true);
                ITeslaProducer producer = itemStack.getCapability(TeslaCapabilities.CAPABILITY_PRODUCER, null);
                System.out.println("---Tesla---");
                System.out.println("Take 1000: " + producer.takePower(1000, false));
            } else if (itemStack.hasCapability(CapabilityEnergy.ENERGY, null)) {
                event.setCanceled(true);
                IEnergyStorage holder = itemStack.getCapability(CapabilityEnergy.ENERGY, null);
                System.out.println("---Forge Energy---");
                System.out.println("Take 1000: " + holder.extractEnergy(1000, false));
            }
        }
    }

    @SubscribeEvent
    public void onInteractConsumer(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() != Blocks.DIAMOND_BLOCK) return;

        ItemStack itemStack = event.getItemStack();
        if (itemStack != null) {
            if (event.getEntityPlayer().isSneaking() && itemStack.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER, null)) {
                event.setCanceled(true);
                ITeslaConsumer consumer = itemStack.getCapability(TeslaCapabilities.CAPABILITY_CONSUMER, null);
                System.out.println("---Tesla---");
                System.out.println("Give 1000: " + consumer.givePower(1000, false));
            } else if (itemStack.hasCapability(CapabilityEnergy.ENERGY, null)) {
                event.setCanceled(true);
                IEnergyStorage holder = itemStack.getCapability(CapabilityEnergy.ENERGY, null);
                System.out.println("---Forge Energy---");
                System.out.println("Give 1000: " + holder.receiveEnergy(1000, false));
            }
        }
    }
}