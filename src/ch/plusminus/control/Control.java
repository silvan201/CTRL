package ch.plusminus.control;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import ch.plusminus.control.commands.addplugin;
import ch.plusminus.control.commands.control;
import ch.plusminus.control.commands.inv;
import ch.plusminus.control.commands.item;
import ch.plusminus.control.commands.serverinfo;
import ch.plusminus.control.commands.serverram;
import ch.plusminus.control.commands.setlife;
import ch.plusminus.control.commands.setmaxusers;
import ch.plusminus.control.commands.worlds;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class Control extends JavaPlugin {
	
	public static File config = new File("plugins/Control/config.cfg");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);

	
	public void onEnable(){
		
		if(!cfg.contains("slots")){
			cfg.set("slots", Bukkit.getMaxPlayers());
			try {
				cfg.save(config);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.getCommand("control").setExecutor(new control(this));
		this.getCommand("item").setExecutor(new item(this));
		this.getCommand("inv").setExecutor(new inv(this));
		this.getCommand("serverram").setExecutor(new serverram(this));
		this.getCommand("serverinfo").setExecutor(new serverinfo(this));
		this.getCommand("worlds").setExecutor(new worlds(this));
		this.getCommand("setlife").setExecutor(new setlife(this));
		this.getCommand("addplugin").setExecutor(new addplugin(this));
	//	this.getCommand("setmaxusers").setExecutor(new setmaxusers(this));
		System.out.println("Controller wurde aktiviert");
	}
	
	

	public void onDisable(){
		System.out.println("Controller wurde deaktiviert");
	}
}
