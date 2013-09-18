package info.mdcraft.parkour.tokens;

import java.util.ArrayList;
import java.util.List;

import info.mdcraft.parkour.manager.SettingsManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TokenManager {
	
	SettingsManager sm = SettingsManager.getInstance();
	
	public void setTokens(Player p, int amount){
		String name = p.getName();
		sm.getData().set(name + ".tokens", amount);
		sm.saveData();
		p.setLevel(amount);
	}
	
	public Integer getTokens(Player p){
		String name = p.getName();
		int t = sm.getData().getInt(name + ".tokens");
		if (t > 0){
		return t;
		}else{
			return 0;
		}
		
	}
	
	public void addTokens(Player p, int amount){
		int c = getTokens(p);
		int a = amount;
		int t = c + a;
		setTokens(p, t);
		sm.saveConfig();
		p.setLevel(t);
	}
	
	public void removeTakens(Player p, int amount){
		int c = getTokens(p);
		int a = amount;
		if (c < a){
			sendNoTokens(p);
		}
		int t = c - a;
		setTokens(p, t);
		sm.saveData();
		p.setLevel(t);
	}
	
	public boolean canCost(Player p, int amount){
		int c = getTokens(p);
		int a = amount;
		if (c < a){
			return false;
		}
		return true;
	}
	
	public void sendTokensBal(Player p){
		int t = getTokens(p);
		p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Tokens: " + 
						ChatColor.AQUA + "" + ChatColor.BOLD + t);
	}
	
	public void sendNoTokens(Player p){
		p.sendMessage(ChatColor.RED + "You do not have enough tokens!");
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cGet more tokens from &9&nhttp://mdcraftfactions.enjin.com/ &cfor &a&lFREE!"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Or visit store to get more Tokens"));
	}
	
	public void getTokenMenuItem(Player p){
		ItemStack n = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = n.getItemMeta();
		im.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Token Shop");
		List<String> ll = new ArrayList<String>();
		ll.add("Click to open Token Shop!");
		ll.add(ChatColor.GRAY + "======================");
		ll.add("Do not have enough tokens? ");
		ll.add("Get more tokens from our website for FREE");
		im.setLore(ll);
		n.setItemMeta(im);
		p.getInventory().clear();
		p.getInventory().setItem(0, n);
	}
}
