package io.github.DJR1996.Main;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class iGrief extends JavaPlugin {
	
	public static PluginDescriptionFile pdf;
	public static FileConfiguration config;
	public static JavaPlugin plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		plugin = this;
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		PluginManager manager = this.getServer().getPluginManager();
		pdf = getDescription();
		manager.registerEvents(new iGriefListener(), this);
		getCommand("ig").setExecutor(new iGriefCommand());
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info("[iGrief] "  + pdf.getName() + " v" + pdf.getVersion());
		this.logger.info("[iGrief] " + pdf.getName() + " is now enabled!");
		
	}
	
	public void onReload(){
		config.options().copyDefaults(false);
		saveConfig();
		reloadConfig();
	}
	
	public void onDisable(){
		saveConfig();
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " is now disabled.");
		
	}

}

