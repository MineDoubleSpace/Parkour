package info.mdcraft.parkour.listener;

import java.util.ArrayList;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class DoubleTapListener implements Listener{
	
	LocationManager l = new LocationManager();
	SettingsManager s = SettingsManager.getInstance();
	EffectsManager em =  new EffectsManager();
	public Plugin plugin;
	
	ArrayList <Player> p = new ArrayList<Player>();
	
	public DoubleTapListener(Plugin i) {
		plugin = i;
	}

	@EventHandler
	public void onPlayerDoubleTap (PlayerToggleFlightEvent event){
		Player pl = event.getPlayer();
		if (pl.getLocation().subtract(0,2,0).getBlock().getType() == Material.AIR){ 
			event.setCancelled(true);
			return;
		}
		if(getHunger(pl) < 20) {
			event.setCancelled(true);
			pl.playSound(pl.getLocation(), Sound.SLIME_WALK, 10 ,10);
			pl.setVelocity(pl.getLocation().getDirection());
			Vector v = new Vector(pl.getVelocity().getX(), -20.0,  pl.getVelocity().getZ());
			pl.setVelocity(v);
			return;
		}
		em.launchPlayer(1, pl);
		pl.setFoodLevel(getHunger(pl) - 20);
		pl.playSound(pl.getLocation(), Sound.ENDERDRAGON_WINGS, 10 ,10);
		pl.setAllowFlight(false);
		pl.setAllowFlight(true);
		pl.setFlying(false);
		event.setCancelled(true);
		return;
	}
	
	@EventHandler
	public void onPlayerJoinDouble(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.setAllowFlight(true);

	}
	
	
	@EventHandler
	public void onplayerjoinhunger(PlayerJoinEvent e){
		final Player pl = e.getPlayer();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				int h = getHunger(pl);
				if (h > 20){
					pl.setFoodLevel(20);
				}else {
				int t = h + 2;
				pl.setFoodLevel(t);
				}
			}
		}, 5, 5);
	}
	
	
	
	public int getHunger(Player pl){
		int h = pl.getFoodLevel();
		if (h < 0){
			pl.setFoodLevel(0);
			return 0;
		}else if (h == 0){
			pl.setFoodLevel(0);
			return 0;
		}else if (h > 20){
			pl.setFoodLevel(20);
			return 20;
		}else { return h; }
	}
	
	public void removeHunger(Player pl){
		int c = getHunger(pl);
		pl.setFoodLevel(c - 6);
	}
}


