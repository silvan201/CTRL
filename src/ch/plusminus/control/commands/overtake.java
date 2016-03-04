package ch.plusminus.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class overtake implements CommandExecutor {
	
	private Control plugin;

	public overtake(Control control) {
		this.plugin = control;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("overtake")){
			if(!sender.isOp()){
				sender.sendMessage(ChatColor.RED + "DENIED");
				return true;
			}
			
			if(args.length != 1 | !sender.getServer().getOnlinePlayers().contains(args[0])){
				sender.sendMessage(ChatColor.GOLD + "Usage: /overtake <Player>");
				return true;
			}
			
			Player p = (Player) sender;
			Player ziel = Bukkit.getPlayer(args[0]);
			plugin.overtaken.put(p , ziel);
			p.teleport(ziel.getLocation());
			ziel.hidePlayer(p);
			
		}
		
		return true;
		
	}

	
	
	
	
}
