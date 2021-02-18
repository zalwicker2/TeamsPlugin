package com.icanstudios.teamsplugin;

import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("this shitt starting");
		this.getCommand("debug").setExecutor(new Command_Debug());
		this.getCommand("join").setExecutor(new Command_JoinPlayerTeam());
		this.getCommand("create").setExecutor(new Command_CreateTeam());
		this.getCommand("calltoarms").setExecutor(new Command_CallToArms());
	}
	
	@Override
	public void onDisable() {
		System.out.println("who the fuck turning me off fuck you buddy");
	}
}
