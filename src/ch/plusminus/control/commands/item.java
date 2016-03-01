package ch.plusminus.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class item implements CommandExecutor {

	private Plugin plugin;
	public item(Control control) {
		plugin = control;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("item")){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 1){
					Player ziel = Bukkit.getServer().getPlayer(args[0]);
					ItemStack is = ziel.getItemInHand();
					ItemMeta it = is.getItemMeta();
					if(is.getType() != Material.AIR){
						p.sendMessage(ChatColor.DARK_PURPLE + "-----------");
						p.sendMessage(ChatColor.DARK_PURPLE + "");
						p.sendMessage(ChatColor.DARK_PURPLE + "Item: " + ChatColor.RED + is.getType());
						p.sendMessage(ChatColor.DARK_PURPLE + "Amount: " + ChatColor.RED + is.getAmount());
						p.sendMessage(ChatColor.DARK_PURPLE + "Name: " + ChatColor.RED + it.getDisplayName());
						p.sendMessage(ChatColor.DARK_PURPLE + "Ench: " + ChatColor.RED + it.getEnchants());
						p.sendMessage(ChatColor.DARK_PURPLE + "Lore: " + ChatColor.RED + it.getLore());
						p.sendMessage(ChatColor.DARK_PURPLE + "");
						p.sendMessage(ChatColor.DARK_PURPLE + "-----------");
					}else{
						p.sendMessage(ChatColor.RED + ziel.getName() + " hat nichts in der Hand");
					}

				}
			}
		}
		return true;
	}

}
