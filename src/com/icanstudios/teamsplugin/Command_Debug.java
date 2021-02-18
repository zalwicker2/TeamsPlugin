package com.icanstudios.teamsplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Debug implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			System.out.print(p.getUniqueId().toString() + " ");
		}
		return true;
	}
}
