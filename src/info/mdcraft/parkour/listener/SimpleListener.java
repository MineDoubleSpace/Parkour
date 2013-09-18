package info.mdcraft.parkour.listener;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;
import net.minecraft.server.v1_6_R2.Packet205ClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class SimpleListener implements Listener{
	
	LocationManager l = new LocationManager();
	SettingsManager s = SettingsManager.getInstance();
	EffectsManager em =  new EffectsManager();
	public Plugin pl;
	
	public SimpleListener(Plugin i) {
		pl = i;
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (event.getCause() == DamageCause.VOID){
				final Player player = (Player) event.getEntity();
				player.setHealth(0.0);
				Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
					@Override
					public void run() {
						Packet205ClientCommand packet = new Packet205ClientCommand();
						packet.a = 1;
						((CraftPlayer)player).getHandle().playerConnection.a(packet);
						player.teleport(l.getCheckPoint(player));
					}
				},5L);
				return;
			}
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		final Player player = e.getEntity();
		e.setDeathMessage("");
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			@Override
			public void run() {
				Packet205ClientCommand packet = new Packet205ClientCommand();
				packet.a = 1;
				((CraftPlayer)player).getHandle().playerConnection.a(packet);
				player.teleport(l.getCheckPoint(player));
			}
		}, 10L);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage("");
		if (p.hasPlayedBefore()){
		String s = ChatColor.BLUE + "Welcome back " + ChatColor.RED + "<player>" + ChatColor.BLUE + " to " + ChatColor.GOLD + "MDCraft Parkour!";
		s = s.replaceAll("<player>", p.getName());
		p.sendMessage(s);
		}else {
			String s = ChatColor.BLUE + "Welcome " + ChatColor.RED +"<player>" + ChatColor.BLUE + " to " + ChatColor.GOLD + "MDCraft Parkour!";
			s = s.replaceAll("<player>", p.getName());
			p.sendMessage(s);
		}
	}
	@EventHandler
	public void onPlayerJoin (PlayerQuitEvent e){
		e.getPlayer().setFoodLevel(0);
		e.setQuitMessage("");
	}
	
	@EventHandler
	public void onEntityExplosion(EntityExplodeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerBuild(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if (p.isOp()){
			return;
		}else{
			e.setCancelled(true);
		}
	}

}
