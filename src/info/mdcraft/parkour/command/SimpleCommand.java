package info.mdcraft.parkour.command;

import info.mdcraft.parkour.main.plugin;
import info.mdcraft.parkour.manager.LocationManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimpleCommand implements CommandExecutor{
	
	LocationManager lm = new LocationManager();
	public plugin p;

	public SimpleCommand(plugin i) {
		p = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkpoint") || cmd.getName().equalsIgnoreCase("cp")){
			if (sender instanceof Player){
				if (!sender.hasPermission("parkour.checkpoint")){
					sender.sendMessage(ChatColor.RED + "No Permissions!");
					return true;
				}
				Player p = (Player) sender;
				if (args.length == 0){
					return true;
				}
				if (args[0].equalsIgnoreCase("set")){
					if (args.length == 1){
					lm.setCheckPoint(p);
					p.sendMessage(ChatColor.GREEN + "CheckPoint Updated");
					return true;
					}
					if (args.length > 1){
						Player t = Bukkit.getServer().getPlayer(args[1]);
						if (t == null){
							p.sendMessage(ChatColor.RED + "Player not found");
							return true;
						}
						lm.setCheckPoint(t);
						t.sendMessage(ChatColor.GREEN + "CheckPoint updated");
						p.sendMessage(ChatColor.GREEN + "CheckPoint updated");
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
	
	

}
