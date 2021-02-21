package com.icanstudios.teamsplugin;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static JavaPlugin plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		System.out.println("this shitt starting");
		this.getCommand("debug").setExecutor(new Command_Debug());
		this.getCommand("join").setExecutor(new Command_JoinPlayerTeam());
		this.getCommand("create").setExecutor(new Command_CreateTeam());
		this.getCommand("calltoarms").setExecutor(new Command_CallToArms());
		this.getCommand("accept").setExecutor(new Command_Accept());
	}
	
	@Override
	public void onDisable() {
		System.out.println("who the fuck turning me off fuck you buddy");
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e) {
		Player caller = e.getPlayer();
		Entity clicked = e.getRightClicked();
		ItemStack item = caller.getInventory().getItemInOffHand();
		System.out.println(item.getType().equals(Material.SADDLE) + " " + (clicked instanceof Player));
		if(item.getType().equals(Material.SADDLE) && clicked instanceof Player) {
			Player ride = (Player) clicked;
			ride.addPassenger(caller);
		}
	}
	
}
