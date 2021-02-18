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
			Team newTeam = new Team(args[0]);
			newTeam.addPlayer(plr);
		}
		return false;
	}
}
