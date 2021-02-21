package com.icanstudios.teamsplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Accept implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			TeleportRequest activeRequest = TeleportRequest.getActiveTeleportRequestFor(plr);
			if(activeRequest != null) {
				activeRequest.acceptTeleport();
				plr.sendMessage("Teleport accepted. Teleport will commence in 5 seconds.");
				return true;
			}
			plr.sendMessage("Teleport failed. No active request.");
			return false;
		}
		return false;
	}
}
