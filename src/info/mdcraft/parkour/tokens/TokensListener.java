package info.mdcraft.parkour.tokens;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class TokensListener implements Listener{
	
	LocationManager l = new LocationManager();
	EffectsManager em = new EffectsManager();
	SettingsManager s = SettingsManager.getInstance();
	TokenManager tm = new TokenManager();
	public Plugin pl;
	
	ArrayList<Player> c = new ArrayList<Player>();
	
	public TokensListener(Plugin i) {
		pl = i;
	}
	
	@EventHandler
	public void onTokensAdd(PlayerMoveEvent e){
		final Player p = e.getPlayer();
		if (c.contains(p)){ return; } 
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.DIAMOND_BLOCK){
			tm.addTokens(p, 5);
			c.add(p);
			p.sendMessage(ChatColor.AQUA + "5 Tokens added!");
			Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
				
				@Override
				public void run() {
					c.remove(p);
					
				}
			}, 20*60*20);
		}
		
	}
	
	@EventHandler
	public void onPlayerJoinToken(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.setLevel(tm.getTokens(p));
		p.sendMessage(ChatColor.GREEN + "You have " + ChatColor.GOLD + tm.getTokens(p) + ChatColor.GREEN + " Tokens" );
		tm.getTokenMenuItem(p);
	}
	

	

}
