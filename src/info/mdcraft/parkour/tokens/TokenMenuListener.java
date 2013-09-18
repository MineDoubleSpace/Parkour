package info.mdcraft.parkour.tokens;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

public class TokenMenuListener implements Listener{
	
	LocationManager l = new LocationManager();
	EffectsManager em = new EffectsManager();
	SettingsManager s = SettingsManager.getInstance();
	TokenManager tm = new TokenManager();
	
	public Plugin plugin;
	
	public TokenMenuListener(Plugin instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerMoveItem(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if (p.isOp()){
			return;
		}else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void OnPlayerDeathMenu(PlayerDeathEvent e){
		e.getDrops().clear();
	}
	
	@EventHandler
	public void onPlayerRespawnMenu(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		p.setLevel(tm.getTokens(p));
		tm.getTokenMenuItem(p);
	}
	
	@EventHandler
	public void onPlayerPickUp (PlayerPickupItemEvent e){
		e.setCancelled(true);
	}
	
	

}
