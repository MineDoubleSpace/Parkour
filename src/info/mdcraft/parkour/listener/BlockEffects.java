package info.mdcraft.parkour.listener;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class BlockEffects implements Listener{
	
	LocationManager l = new LocationManager();
	SettingsManager s = SettingsManager.getInstance();
	EffectsManager em =  new EffectsManager();
	public Plugin p;
	
	public BlockEffects(Plugin i) {
		p = i;
	}
	/*
	 * Redstone launchPlayer, 3
	 * Lapis Speed2, 3 seconds
	 * Sponge insta kill
	 * SoulSand Slowness, 3 seconds
	 * Obsidian blindness, 3 seconds
	 * 
	 */
	
	
	@EventHandler
	public void RedStoneEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.REDSTONE_BLOCK){
			em.launchPlayer(3, p);
            return;
		}
	}
	
	@EventHandler
	public void LapisEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.LAPIS_BLOCK){
			em.addPotionEffect(p, PotionEffectType.SPEED, 60, 2);
            return;
		}
	}
	
	@EventHandler
	public void SpongeEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.SPONGE){
			p.setHealth(0.0);
            return;
		}
	}
	
	@EventHandler
	public void SoulSandEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.SOUL_SAND){
			em.addPotionEffect(p, PotionEffectType.SLOW, 60, 5);
            return;
		}
	}
	
	@EventHandler
	public void ObsidianEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.OBSIDIAN){
			em.addPotionEffect(p, PotionEffectType.BLINDNESS, 60, 5);
            return;
		}
		
		
	}
	 
	@EventHandler
	public void enderPortalEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.COAL_BLOCK){
			Location loc = l.getCheckPoint(p);
			p.teleport(loc);
            return;
		}
		
		
	}
	
/*	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void PistonEffects(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (l.getBlock(p) == null){ return; }
		if (l.getBlock(p).getType() == Material.PISTON_BASE){
			em.addPotionEffect(p, PotionEffectType.BLINDNESS, 60, 5);
			Block b = l.getBlock(p);
			Block i;
//			Block d = i.setType(Material.PISTON_EXTENSION);
			PistonBaseMaterial piston = (PistonBaseMaterial) b.getState().getData();
			piston.setPowered(true);
			b.setData(piston.getData());
//			i.setType(Material.PISTON_EXTENSION);
//			PistonExtensionMaterial pe = (PistonExtensionMaterial) i.getState().getData();
//			i.setData(pe.getData());
//			i.getState().update();
			b.getState().update();
            return;
		}
	
	*/

}
