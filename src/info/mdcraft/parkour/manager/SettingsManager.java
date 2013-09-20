package info.mdcraft.parkour.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;


public class SettingsManager {
	

	private SettingsManager() {
	}

	static SettingsManager instance = new SettingsManager();

	public static SettingsManager getInstance() {
		return instance;
	}
	Plugin p;

	FileConfiguration config;
	File cfile;
	

	public void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		cfile = new File(p.getDataFolder(), "config.yml");
		if (!cfile.exists()){
			p.saveDefaultConfig();
		}
		
		config = p.getConfig();
	}
	
	
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	
	
	public void saveConfig() {
		try {
			config.save(cfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.DARK_RED + "Could not save config.yml");
		}
	}
	
	
	
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(cfile);
	}
	
	
	
	
	public PluginDescriptionFile getDes() {
		return p.getDescription();
	}
	
//	                    ======= ########### ========
	
	
	FileConfiguration Playerdata;
	File pfile;
	
	public void DataSetup (Plugin p){
		
	pfile = new File(p.getDataFolder(), "Playerdata.yml");
		
		if (!pfile.exists()){
			try {
				pfile.createNewFile();
			}catch (IOException e){
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create Playerdata.yml");
			}
		}
		Playerdata = YamlConfiguration.loadConfiguration(pfile);
	}
	
	public FileConfiguration getData(){
		return Playerdata;
	}
	
	public void saveData(){
		try {
			Playerdata.save(pfile);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.DARK_RED + "Could not save Playerdata.yml");
		}
	}
	
	public void reloadData() {
		Playerdata = YamlConfiguration.loadConfiguration(pfile);
	}
}