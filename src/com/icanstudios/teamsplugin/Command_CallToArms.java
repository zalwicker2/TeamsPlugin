package com.icanstudios.teamsplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_CallToArms implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			Team plrTeam = Team.getTeamFromPlayer(plr);
			for(Player plrs : plrTeam.getOnlineMembers()) {
				if(plrs.equals(plr)) { continue; }
				plrs.sendMessage(plr.getCustomName() + " is dying. Help them you worthless piece of shit.");
			}
		}
		return false;
	}
}
