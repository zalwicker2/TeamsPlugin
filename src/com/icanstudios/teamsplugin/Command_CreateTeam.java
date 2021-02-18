package com.icanstudios.teamsplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_CreateTeam implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			String teamName = "";
			if(args.length == 0) {
				plr.sendMessage("give the team a name dumbass");
				return false;
			} else {
				for(int i = 0; i < args.length; i++) {
					teamName+=args[i]+" ";
				}
			}
			teamName.trim();
			Team newTeam = new Team(teamName);
			newTeam.addPlayer(plr);
			plr.sendMessage("Team " + teamName + " created.");
			return true;
		}
		return false;
	}
}
