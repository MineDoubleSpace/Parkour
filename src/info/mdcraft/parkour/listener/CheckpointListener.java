package info.mdcraft.parkour.listener;

import java.util.ArrayList;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;
import info.mdcraft.parkour.tokens.TokenManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class CheckpointListener implements Listener{
	
	LocationManager l = new LocationManager();
	EffectsManager em = new EffectsManager();
	SettingsManager s = SettingsManager.getInstance();
	TokenManager tm = new TokenManager();
	public Plugin pl;
	
	ArrayList<Player> c = new ArrayList<Player>();
	
	public CheckpointListener(Plugin i) {
		pl = i;
	}

	@EventHandler
	public void CheckPointCheck(PlayerMoveEvent e){
		final Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.GOLD_BLOCK){
		if (c.contains(p)) { return; }
			c.add(p);
			l.setCheckPoint(p);
			tm.removeTakens(p, 1);
			p.sendMessage(ChatColor.DARK_GREEN + "Check point updated!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				@Override
				public void run() {
					c.remove(p);
				}
			}, 100);
	}
		}
	}
	


