package info.mdcraft.parkour.command;

import java.util.ArrayList;

import info.mdcraft.parkour.manager.EffectsManager;
import info.mdcraft.parkour.manager.LocationManager;
import info.mdcraft.parkour.manager.SettingsManager;
import info.mdcraft.parkour.tokens.TokenManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TokenCommand implements CommandExecutor{
	
	public Plugin pl;
	
	ArrayList<Player> c = new ArrayList<Player>();
	
	public TokenCommand(Plugin i) {
		pl = i;
	}
	
	LocationManager l = new LocationManager();
 	EffectsManager em = new EffectsManager();
 	SettingsManager s = SettingsManager.getInstance();
 	TokenManager tm = new TokenManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("token")){
			if (args.length == 0){
				sender.sendMessage(ChatColor.RED + "/token add <Player> <Amount>");
				sender.sendMessage(ChatColor.RED + "/token set <Player> <Amount>");
			} if (args[0].equalsIgnoreCase("add")){
				Player p = Bukkit.getServer().getPlayerExact(args[1]);
				if (p == null){
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				try{
				int a = Integer.parseInt(args[2]);
				tm.addTokens(p, a);
				p.sendMessage(ChatColor.GREEN + "Yay! you just recived " + a + " Tokens!");
				sender.sendMessage(ChatColor.GREEN + "Tokens Added!");
				return true;
				} catch (Exception e){
					sender.sendMessage(ChatColor.DARK_RED + "Error! Please use the format below");
					sender.sendMessage(ChatColor.RED + "/token add <Player> <Amount>");
					sender.sendMessage(ChatColor.RED + "/token set <Player> <Amount>");
				}
			} if (args[0].equalsIgnoreCase("set")){
				Player p = Bukkit.getServer().getPlayerExact(args[1]);
				if (p == null){
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				try{
				int a = Integer.parseInt(args[2]);
				tm.setTokens(p, a);
				p.sendMessage(ChatColor.GREEN + "Yay! you just recived " + a + " Tokens!");
				sender.sendMessage(ChatColor.GREEN + "Tokens Added!");
				return true;
				} catch (Exception e){
					sender.sendMessage(ChatColor.DARK_RED + "Error! Please use the format below");
					sender.sendMessage(ChatColor.RED + "/token add <Player> <Amount>");
					sender.sendMessage(ChatColor.RED + "/token set <Player> <Amount>");
				}
			}
		}
		return false;
	}

}
