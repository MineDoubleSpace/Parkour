package info.mdcraft.parkour.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class LocationManager {
	
	
	SettingsManager s = SettingsManager.getInstance();
	
	
	public Block getBlock(Player p){
		Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
		if (b.getType() != Material.AIR ){
			return b;
		}else {
			return null;
		}
	}
	
	public void setCheckPoint(Player p){
		s.getData().set(p.getName() + ".checkpoint.world", p.getLocation().getWorld().getName());
		s.getData().set(p.getName() + ".checkpoint.x", p.getLocation().getBlockX());
		s.getData().set(p.getName() + ".checkpoint.y", p.getLocation().getBlockY());
		s.getData().set(p.getName() + ".checkpoint.z", p.getLocation().getBlockZ());
		s.saveData();
	}
	
	public Location getCheckPoint(Player p){
		World w = Bukkit.getWorld(s.getData().getString(p.getName() + ".checkpoint.world"));
		int x = s.getData().getInt(p.getName() + ".checkpoint.x");
		int y = s.getData().getInt(p.getName() + ".checkpoint.y");
		int z = s.getData().getInt(p.getName() + ".checkpoint.z");
		Location loc = new Location(w, x, y, z);
		return loc;
	}
	

}
