package ch.plusminus.control.commands;

import java.io.IOException;
import java.net.URL;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;
import ch.plusminus.control.SaveURL;

public class addplugin implements CommandExecutor {
	private Plugin plugin;
	public addplugin(Control control) {
		this.plugin = control;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("addplugin")){
			if(sender.isOp()){
				try {
					SaveURL.SaveURL(args[0], new URL(args[1]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return true;
	}
}
