package com.icanstudios.teamsplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Team implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage("Type an actual request dumbass.");
			return false;
		}
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			String subCommand = args[0];
			switch(subCommand) {
				case "create":
					String teamName = "";
					if(args.length < 2) {
						plr.sendMessage("give the team a name dumbass");
						return false;
					} else {
						for(int i = 1; i < args.length; i++) {
							teamName+=args[i]+" ";
						}
					}
					Team newTeam = new Team(teamName.trim());
					newTeam.addPlayer(plr);
					plr.sendMessage("Team " + teamName + " created.");
					return true;
				case "join":
					if(args.length == 1) {
						plr.sendMessage("You're just gonna nothing? Good for you.");
						return false;
					} else {
						Player toJoin = Bukkit.getServer().getPlayer(args[0]);
						Team joining = Team.getTeamFromPlayer(toJoin);
						joining.addPlayer(plr);
						plr.sendMessage("You have joined " + joining.getName() + "!");
						return true;
					}
			}
		}
		return false;
	}
}
