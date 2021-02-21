package com.icanstudios.teamsplugin;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportRequest extends BukkitRunnable {
	
	private static HashMap<Player, TeleportRequest> activeRequests = new HashMap<Player, TeleportRequest>();
	
	public static boolean checkForTeleportRequest(Player p) {
		return activeRequests.containsKey(p);
	}
	
	public static TeleportRequest getActiveTeleportRequestFor(Player p) {
		if(checkForTeleportRequest(p)) {
			return activeRequests.get(p);
		}
		return null;
	}
	
	private final int COOLDOWN_TIME = 30;
	private final int SAFETY_TIME = 5;
	
	private int count = COOLDOWN_TIME;
	private int stage = 2;
	
	private Player sender;
	private Player recipiant;
	private boolean accepted = false;
	
	public boolean sendTeleportRequest(Player sender, Player recipiant) {
		if(checkForTeleportRequest(recipiant)) {
			return false;
		}
		this.sender = sender;
		this.recipiant = recipiant;
		activeRequests.put(recipiant, this);
		
		return true;
	}
	
	public void run() {
		if(count == 0) {
			stage--;
			if(stage == 0) {
				if(accepted) {
					recipiant.teleport(sender);
				}
				cancel();
			}
		}
		count--;
	}
	
	public void acceptTeleport() {
		stage = 1;
		count = SAFETY_TIME;
		accepted = true;
		activeRequests.remove(recipiant);
	}
}
