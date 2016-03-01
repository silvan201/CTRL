package ch.plusminus.control.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class setmaxusers implements CommandExecutor {

	
	private Plugin plugin;
	public setmaxusers(Control control) {
		this.plugin = control;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender.isOp()){
				if(args.length > 0){
					Control.cfg.set("slots", args[0]);
					try {
						Control.cfg.save(Control.config);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		return true;
	}
}
