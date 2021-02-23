package com.icanstudios.teamsplugin;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Team {
	
	private static ArrayList<Team> teamList = new ArrayList<Team>();
	
	public static Team getTeamFromPlayer(Player plr) {
		for(Team t : teamList) {
			if(t.idInTeam(plr.getUniqueId())) {
				return t;
			}
		}
		return null;
	}
	
	private String name;
	private UUID[] members;
	
	public Team(String name) {
		this.name = name;
		members = new UUID[20];
		teamList.add(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean addPlayer(Player plr) {
		for(int i = 0; i < members.length; i++) {
			UUID id = plr.getUniqueId();
			if(members[i] == null) {
				members[i] = id; // 
				return true; // player successfully added
			}
			if(members[i].equals(id)) {
				return false; // player already exists
			}
		}
		return false; // too many players or there is a bug
	}
	
	public boolean addPlayer(UUID id) {
		for(int i = 0; i < members.length; i++) {
			if(members[i] == null) {
				members[i] = id; // 
				return true; // player successfully added
			}
			if(members[i].equals(id)) {
				return false; // player already exists
			}
		}
		return false; // too many players or there is a bug
	}
	
	public boolean removePlayer(Player plr) {
		UUID id = plr.getUniqueId();
		int i = 0;
		UUID currentId = members[i];
		while(!currentId.equals(id) && i < (members.length - 1)) {
			i++;
			currentId = members[i];
		}
		while(currentId != null && i < (members.length - 1)) {
			members[i] = members[i + 1];
			i++;
		}
		return true;
	}
	
	public boolean removePlayer(UUID id) {
		int i = 0;
		UUID currentId = members[i];
		while(!currentId.equals(id) && i < (members.length - 1)) {
			i++;
			currentId = members[i];
		}
		while(currentId != null && i < (members.length - 1)) {
			members[i] = members[i + 1];
			i++;
		}
		if(members.length == 0) {
			teamList.remove(this);
		}
		return true;
	}
	
	public Player[] getOnlineMembers() {
		ArrayList<Player> onlineMembers = new ArrayList<Player>();
		for(Player plr : Bukkit.getServer().getOnlinePlayers()) {
			if(idInTeam(plr.getUniqueId())) {
				onlineMembers.add(plr);
			}
		}
		Player[] players = new Player[onlineMembers.size()];
		return onlineMembers.toArray(players);
	}
	
	public boolean idInTeam(UUID id) {
		for(int i = 0; i < members.length; i++) {
			if(members[i] == null) {
				return false;
			}
			if(members[i].equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = "\n";
		s+="Faction: " + name + "\n";
		s+="Members: ";
		int i = 0;
		while(members[i] != null) {
			s+=members[i].toString() + "\n";
			i++;
		}
		return s;
	}
}
