package com.icanstudios.teamsplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static JavaPlugin plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		System.out.println("this shitt starting");
		getServer().getPluginManager().registerEvents(this, this);
		this.getCommand("debug").setExecutor(new Command_Debug(this.getConfig().getString("default-weapon").toUpperCase()));
		this.getCommand("circle").setExecutor(new Command_FireworkCircle());
		this.getCommand("team").setExecutor(new Command_Team());
		this.getCommand("calltoarms").setExecutor(new Command_CallToArms());
		this.getCommand("accept").setExecutor(new Command_Accept());
	}
	
	@Override
	public void onDisable() {
		System.out.println("who the fuck turning me off fuck you buddy");
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		Player caller = e.getPlayer();
		Entity clicked = e.getRightClicked();
		ItemStack item = caller.getInventory().getItemInOffHand();
		System.out.println(item.getType().equals(Material.SADDLE) + " " + (clicked instanceof Player));
		if(item.getType().equals(Material.SADDLE) && clicked instanceof Player) {
			Player ride = (Player) clicked;
			ride.addPassenger(caller);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChestOpen(InventoryCloseEvent e) {
		if(e.getInventory().getType() == InventoryType.CHEST) {
			Chest c = (Chest) e.getInventory().getHolder();
			if(c.getMetadata("specialChest").size() > 0 && c.getMetadata("specialChest").get(0).asBoolean()) {
				System.out.println("special chest opened");
				System.out.println(c.getInventory().isEmpty());
				if(c.getInventory().isEmpty()) {
					c.setType(Material.AIR);
				}
			}
		}
	}
	
}
