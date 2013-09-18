package info.mdcraft.parkour.tokens;


import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
 
public class TokenMenu implements Listener {
 
        private Inventory inv;
        private ItemStack speed, jump, night, exit, cp;
        LocationManager lm = new LocationManager();
    	EffectsManager em = new EffectsManager();
    	SettingsManager sm = SettingsManager.getInstance();
    	TokenManager tm = new TokenManager();
       
        public TokenMenu(Plugin p) {
        	
                inv = Bukkit.getServer().createInventory(null, 9, "");
                
                speed = createItem(new ItemStack(Material.SUGAR), ChatColor.AQUA + "Crazy Speed", "Add Crazy Speed effect for 2 minutes", 2);
                jump = createItem(new ItemStack(Material.REDSTONE), ChatColor.AQUA + "Crazy Jump Boost", "Add Crazy jump boost for 1 minute", 2);
                night = createItem(new ItemStack(Material.GLOWSTONE_DUST), ChatColor.AQUA + "Night Vision", "Add Night Vision for 8 minutes", 2);
                exit = createItem(new ItemStack(Material.IRON_FENCE), ChatColor.DARK_RED + "Close", "Close this menu", 0);
                cp = createItem(new ItemStack(Material.NAME_TAG), ChatColor.GOLD + "Check Point", "Sets your current location as your CheckPoint", 5);
               
                inv.setItem(0, speed);
                inv.setItem(1, jump);
                inv.setItem(2, night);
                inv.setItem(7, cp);
                inv.setItem(8, exit);
                
                Bukkit.getServer().getPluginManager().registerEvents(this, p);
        }
       
        private ItemStack createItem(ItemStack m, String name, String lore, int cost) {
                ItemStack i = m;
                ItemMeta im = i.getItemMeta();
                im.setDisplayName(name);
                List<String> llist = new ArrayList<String>();
                llist.add("");
                llist.add(lore);

                    if (cost == 0){
                    }else if (cost == 1){
                         llist.add("");
                         llist.add(ChatColor.GRAY + "========================");
                    	 llist.add("");
                         llist.add("");
                		 llist.add(ChatColor.GOLD + "Cost: " + cost + " Token");
                	}else {
                		llist.add("");
                        llist.add(ChatColor.GRAY + "========================");
                        llist.add("");
                		llist.add(ChatColor.GOLD + "Cost: " + cost + " Tokens");
                }
                im.setLore(llist);
                i.setItemMeta(im);
                return i;
        }
       
        public void show(Player p) {
                p.openInventory(inv);
        }
        
    	@EventHandler
    	public void onPlayerClickEventmenu(PlayerInteractEvent e){
    		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
    		Player pl = e.getPlayer();
    		ItemStack i = e.getItem();
    		if (i.getType() != Material.NETHER_STAR){ return; }
    		if (!i.getItemMeta().getDisplayName().contains("Shop")){ return; }
    		show(pl);
    		}
    		return;
    		
    	}
       
        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
                if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())){ return;}
                if (e.getCurrentItem().getItemMeta() == null){ return;}
                Player p = (Player) e.getWhoClicked();
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Crazy Speed")) {
                		if (tm.canCost(p, 2)){
                		em.addPotionEffect(p, PotionEffectType.SPEED, 20*60*2, 10);
                		tm.removeTakens(p, 1);
                        e.setCancelled(true);
                        e.getWhoClicked().closeInventory();
                        return;
                		} 
                		tm.sendNoTokens(p);
                		e.setCancelled(true);
                        e.getWhoClicked().closeInventory();
                		return;
                		
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Crazy Jump Boost")) {
                	if (tm.canCost(p, 2)){
            		em.addPotionEffect(p, PotionEffectType.JUMP, 20*60*1, 10);
            		tm.removeTakens(p, 2);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                    return;
                	} 
                	tm.sendNoTokens(p);
                	e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                	return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Night Vision")) {
                	if (tm.canCost(p, 2)){
            		em.addPotionEffect(p, PotionEffectType.NIGHT_VISION, 20*60*10, 10);
            		tm.removeTakens(p, 2);
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                    return;
                	}
                	tm.sendNoTokens(p);
                	e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                	return;
                    
                }if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Check Point")) {
                	if (tm.canCost(p, 2)){
                	if (p.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR ){
            		lm.setCheckPoint(p);
            		tm.removeTakens(p, 5);
            		p.sendMessage(ChatColor.DARK_GREEN + "Check point updated!");
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                    return;
                	}
                	e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                    p.sendMessage(ChatColor.RED + "You must be standing on a block!");
                    return;
                	}
                	tm.sendNoTokens(p);
                	e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                	return;
                    
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Close")) {
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
            }
        }
}