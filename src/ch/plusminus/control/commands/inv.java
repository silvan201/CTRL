package ch.plusminus.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class inv implements CommandExecutor {
	
	
	private Plugin plugin;
	public inv(Control control) {
		this.plugin = control;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("inv")){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 1){
					Player ziel = Bukkit.getServer().getPlayer(args[0]);
					Inventory inv = ziel.getInventory();
					p.openInventory(inv);

				}
			}
		}
		return true;
	}

}
