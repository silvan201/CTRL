package ch.plusminus.control.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class control implements CommandExecutor {

	private Plugin plugin;
	public control(Control control) {
		plugin = control;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("control")){
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length == 1){
					Player ziel = Bukkit.getServer().getPlayer(args[0]);
					p.sendMessage(ChatColor.DARK_PURPLE + "-----------");
					p.sendMessage(ChatColor.DARK_PURPLE + "");
					p.sendMessage(ChatColor.DARK_PURPLE + "Spieler: " + ChatColor.RED + ziel.getName());
					p.sendMessage(ChatColor.DARK_PURPLE + "Displayname: " + ChatColor.RED + ziel.getDisplayName());
					p.sendMessage(ChatColor.DARK_PURPLE + "Costumname: " + ChatColor.RED + ziel.getCustomName());
					p.sendMessage(ChatColor.DARK_PURPLE + "UUID: " + ChatColor.RED + ziel.getUniqueId());
					p.sendMessage(ChatColor.DARK_PURPLE + "Leben: " + ChatColor.RED + ziel.getHealth());
					p.sendMessage(ChatColor.DARK_PURPLE + "Level: " + ChatColor.RED + ziel.getLevel());
					p.sendMessage(ChatColor.DARK_PURPLE + "Exp: " + ChatColor.RED + ziel.getExp());
					p.sendMessage(ChatColor.DARK_PURPLE + "TotalExp: " + ChatColor.RED + ziel.getTotalExperience());
					p.sendMessage(ChatColor.DARK_PURPLE + "Hunger: " + ChatColor.RED + ziel.getFoodLevel());
					p.sendMessage(ChatColor.DARK_PURPLE + "X: " + ChatColor.RED + ziel.getLocation().getX());
					p.sendMessage(ChatColor.DARK_PURPLE + "Y: " + ChatColor.RED + ziel.getLocation().getY() + "(" + ziel.getEyeLocation().getY() + ")");
					p.sendMessage(ChatColor.DARK_PURPLE + "Z: " + ChatColor.RED + ziel.getLocation().getZ());
					p.sendMessage(ChatColor.DARK_PURPLE + "Yaw: " + ChatColor.RED + ziel.getLocation().getYaw());
					p.sendMessage(ChatColor.DARK_PURPLE + "Pitch: " + ChatColor.RED + ziel.getLocation().getPitch());
					p.sendMessage(ChatColor.DARK_PURPLE + "Velocity: " + ChatColor.RED + ziel.getVelocity());
					p.sendMessage(ChatColor.DARK_PURPLE + "ItemInHand: " + ChatColor.RED + ziel.getItemInHand().getType() + "(" + ziel.getItemInHand().getAmount() + ")");
					p.sendMessage(ChatColor.DARK_PURPLE + "IsFlying: " + ChatColor.RED + ziel.isFlying());
					p.sendMessage(ChatColor.DARK_PURPLE + "IsSneaking: " + ChatColor.RED + ziel.isSneaking());
					p.sendMessage(ChatColor.DARK_PURPLE + "IsSprinting: " + ChatColor.RED + ziel.isSprinting());
					p.sendMessage(ChatColor.DARK_PURPLE + "Gamemode: " + ChatColor.RED + ziel.getGameMode());
					p.sendMessage(ChatColor.DARK_PURPLE + "");
					p.sendMessage(ChatColor.DARK_PURPLE + "-----------");
				}
			}
		}
		return true;
		
		
		
	}


}
