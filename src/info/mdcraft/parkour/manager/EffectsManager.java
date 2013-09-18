package info.mdcraft.parkour.manager;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EffectsManager {
	
	LocationManager l = new LocationManager();
	
	public void launchPlayer(int amount,Player p ){
		p.setVelocity(p.getLocation().getDirection().multiply(amount));
        p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
        return;
	}
	
	
	
	
	public void addPotionEffect(Player p, PotionEffectType effect, int duration, int amp){
	p.addPotionEffect(new PotionEffect(effect, duration, amp));
	
	
	}
	

}
