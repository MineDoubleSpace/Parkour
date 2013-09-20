package info.mdcraft.parkour.main;

import info.mdcraft.parkour.command.SimpleCommand;
import info.mdcraft.parkour.command.TokenCommand;
import info.mdcraft.parkour.listener.BlockEffects;
import info.mdcraft.parkour.listener.CheckpointListener;
import info.mdcraft.parkour.listener.DoubleTapListener;
import info.mdcraft.parkour.listener.SimpleListener;
import info.mdcraft.parkour.manager.SettingsManager;
import info.mdcraft.parkour.tokens.TokenMenu;
import info.mdcraft.parkour.tokens.TokenMenuListener;
import info.mdcraft.parkour.tokens.TokensListener;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class plugin extends JavaPlugin{
	
	SettingsManager s = SettingsManager.getInstance();
	
	@Override
	public void onEnable() {
		System.out.println("[Parkour]"+ChatColor.GREEN + "Loading files...");
		s.setup(this);
		s.DataSetup(this);
		try{
		this.getServer().getWorld(s.getConfig().getString("world")).setPVP(false);
		this.getServer().getWorld(s.getConfig().getString("world")).setAnimalSpawnLimit(0);;
		this.getServer().getWorld(s.getConfig().getString("world")).setMonsterSpawnLimit(0);
		} catch (Exception e){
			System.out.println("[Parkour]World not found!");
		}
		this.getServer().getPluginManager().registerEvents(new CheckpointListener(this), this);
		this.getServer().getPluginManager().registerEvents(new BlockEffects(this), this);
		this.getServer().getPluginManager().registerEvents(new SimpleListener(this), this);
		this.getServer().getPluginManager().registerEvents(new TokensListener(this), this);
		this.getServer().getPluginManager().registerEvents(new DoubleTapListener(this), this);
		this.getServer().getPluginManager().registerEvents(new TokenMenuListener(this), this);
		@SuppressWarnings("unused")
		TokenMenu menu;
		menu = new TokenMenu(this);
		new DoubleTapListener(this).startHungerRegen(this);
		
		
		
		
		this.getCommand("checkpoint").setExecutor(new SimpleCommand(this));
		this.getCommand("cp").setExecutor(new SimpleCommand(this));
		this.getCommand("token").setExecutor(new TokenCommand(this));
		System.out.println("[Parkour]"+ChatColor.GREEN + "Config created and loaded...");
	}
	
	
	
	
	@Override
	public void onDisable() {
		s.saveConfig();
		s.saveData();
	}
}
