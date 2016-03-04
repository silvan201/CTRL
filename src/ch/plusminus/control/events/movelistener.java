package ch.plusminus.control.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import ch.plusminus.control.Control;

public class movelistener implements Listener {
	
	private Control plugin;
	public movelistener(Control control) {
		this.plugin = control;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(plugin.overtaken.containsKey(e.getPlayer())){
			plugin.overtaken.get(e.getPlayer()).teleport(e.getPlayer().getLocation());
		}
		if(plugin.overtaken.containsValue(e.getPlayer())){
			e.setCancelled(true);
			
		}
	}
	
	
	                     
	
	
	

}
