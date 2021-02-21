package com.icanstudios.teamsplugin;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Command_FireworkCircle implements CommandExecutor {
	
	private static final int FIREWORK_COUNT = 16;
	private static final int FIREWORK_RADIUS = 6;
	private static final long DELAY = 7;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player plr = (Player) sender;
			
			Location loc = plr.getLocation();
			
			BukkitRunnable run = new BukkitRunnable() {
				int count = FIREWORK_COUNT;
				@Override
				public void run() {
					if(count == 0) {
						cancel();
					}
					
					double angle = (2 * Math.PI / (double) FIREWORK_COUNT) * (double) count;
					Vector offset = new Vector(-Math.sin(angle), 0, Math.cos(angle)).multiply(FIREWORK_RADIUS);
					Firework f = (Firework) plr.getWorld().spawn(loc.clone().add(offset), Firework.class);
					FireworkMeta fm = f.getFireworkMeta();
					fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(Type.BALL).withColor(Color.RED).build());
					fm.setPower(3);
					f.setFireworkMeta(fm);
					count--;
				}
			};
			
			run.runTaskTimer(Main.plugin, 0L, DELAY);
				
			return true;
		}
		return false;
	}
}
