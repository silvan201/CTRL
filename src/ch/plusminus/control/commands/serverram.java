package ch.plusminus.control.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;

public class serverram implements CommandExecutor {
	
	private Plugin plugin;
	public serverram(Control control) {
		this.plugin = control;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("serverram")){
			CommandSender p = sender;
			if(p.isOp()){
		        int mb = 1024*1024;
		         
		        //Getting the runtime reference from system
		        Runtime runtime = Runtime.getRuntime();
		         
		        p.sendMessage("##### Heap utilization statistics [MB] #####");
		         
		        //Print used memory
		        p.sendMessage("Used Memory:"
		            + (runtime.totalMemory() - runtime.freeMemory()) / mb);
		 
		        //Print free memory
		        p.sendMessage("Free Memory:"
		            + runtime.freeMemory() / mb);
		         
		        //Print total available memory
		        p.sendMessage("Total Memory:" + runtime.totalMemory() / mb);
		 
		        //Print Maximum available memory
		        p.sendMessage("Max Memory:" + runtime.maxMemory() / mb);
			}
		}
		return true;
	}

}
