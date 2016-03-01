package ch.plusminus.control.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class worlds implements CommandExecutor {

	private Plugin plugin;
	public worlds(Control control) {
		this.plugin = control;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("worlds")){
			if(sender.isOp()){
				List<World> w = Bukkit.getServer().getWorlds();
				for(int x = 0 ; x == w.size() + 1 ; x++){
					sender.sendMessage(w.get(x).getName() + " Players: " + w.get(x).getPlayers() + " Loaded Chunks: " + w.get(x).getLoadedChunks().length);

					
				}

			}
		}
		return true;
	}

}
