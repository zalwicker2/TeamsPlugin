package com.icanstudios.teamsplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Debug implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			TeleportRequest req = new TeleportRequest();
			Player recip = Bukkit.getServer().getPlayer(args[0]);
			req.sendTeleportRequest(plr, recip);
			req.runTaskTimer(Main.plugin, 0L, 20L);
		}
		return true;
	}
}
