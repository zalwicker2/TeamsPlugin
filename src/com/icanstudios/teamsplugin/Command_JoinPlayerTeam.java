package com.icanstudios.teamsplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_JoinPlayerTeam implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			if(args.length == 0) {
				plr.sendMessage("You're just gonna nothing? Good for you.");
			} else {
				Player toJoin = Bukkit.getServer().getPlayer(args[0]);
				Team joining = Team.getTeamFromPlayer(toJoin);
				joining.addPlayer(plr);
			}
		}
		return false;
	}
}
