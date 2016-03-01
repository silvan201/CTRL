package ch.plusminus.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class serverinfo implements CommandExecutor {

	private Plugin plugin;
	public serverinfo(Control control) {
		this.plugin = control;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("serverinfo")){
			if(sender.isOp()){
				Server s = Bukkit.getServer();
				sender.sendMessage(ChatColor.DARK_PURPLE + "Welten: " + ChatColor.RED + s.getWorlds().size());
				sender.sendMessage(ChatColor.DARK_PURPLE + "Max Players: " + ChatColor.RED + s.getMaxPlayers());
				sender.sendMessage(ChatColor.DARK_PURPLE + "Bukkitversion: " + ChatColor.RED + s.getBukkitVersion());
				sender.sendMessage(ChatColor.DARK_PURPLE + "Port: " + ChatColor.RED + s.getPort());
				sender.sendMessage(ChatColor.DARK_PURPLE + "Version: " + ChatColor.RED + s.getVersion());


			}
		}
		return true;
	}

}
