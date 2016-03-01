/**
 * 
 */
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

/**
 * @author Silvan
 *
 */
public class setlife implements CommandExecutor {

	private Plugin plugin;
	public setlife(Control control) {
		plugin = control;
	}

	/* (non-Javadoc)
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setlife")){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 2){
					Player z = Bukkit.getServer().getPlayer(args[0]);
					double l = Double.parseDouble(args[1]);
					z.setHealth(l);
		
					
				
				

				}
			}
		}
		return true;
	}

}
