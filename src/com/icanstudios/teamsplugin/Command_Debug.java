package com.icanstudios.teamsplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Command_Debug implements CommandExecutor {
	
	ItemStack item;
	public Command_Debug(String item) {
		this.item = new ItemStack(Material.valueOf(item));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			plr.getInventory().addItem(item);
			System.out.println(org.bukkit.attribute.Attribute.GENERIC_LUCK);
			Location loc = plr.getLocation();
			loc.getWorld().createExplosion(loc, 3);
			Block b = loc.getWorld().getHighestBlockAt(loc);
			b.setType(Material.CHEST);
			Chest c = (Chest) b.getState();
			c.getBlockInventory().addItem(new ItemStack(Material.COBBLESTONE, 3));
			c.setMetadata("specialChest", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
		}
		return true;
	}
}
