package ch.plusminus.control.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import ch.plusminus.control.Control;
import ch.plusminus.control.Rank;

 public class joinlistener implements Listener  {
	
	private Plugin plugin;
	public joinlistener(Control control) {
		this.plugin = control;
	}
	
	@EventHandler
	public void onJoin(PlayerLoginEvent ev){
		
		if(((Control) plugin).getRank(ev.getPlayer().getUniqueId()).equals(Rank.OPERATOR)){
			ev.getPlayer().setOp(true);
		}
		
	}

}
