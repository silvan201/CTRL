package ch.plusminus.control;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.plusminus.control.commands.addplugin;
import ch.plusminus.control.commands.control;
import ch.plusminus.control.commands.inv;
import ch.plusminus.control.commands.item;
import ch.plusminus.control.commands.overtake;
import ch.plusminus.control.commands.serverinfo;
import ch.plusminus.control.commands.serverram;
import ch.plusminus.control.commands.setlife;
import ch.plusminus.control.Rank;
import ch.plusminus.control.commands.worlds;
import ch.plusminus.control.events.joinlistener;
import ch.plusminus.control.events.movelistener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class Control extends JavaPlugin {
	
	private String database = "minecraft";
    private String hostname = "localhost";
    private int port = 3306;
    private String userid = "minecraft";
    private String password = "";
    private String connectionString = "";
    private String prafix = "Log_";
    private String servername = "not set yet";
    private boolean Error = false;
	public static File config = new File("plugins/Control/config.cfg");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);
    
    public HashMap<Player, Player> overtaken = new HashMap<>();

    
	public void onEnable(){
		startCore();
		if(config.exists() && !config.isDirectory()) {
			servername = cfg.getString("servername");
			password = cfg.getString("password");
		}else {
			Error=true;
			try {
				config.createNewFile();
				cfg.set("servername", ";-;");
				cfg.set("password", ";-;");
				cfg.save(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!Error){
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				
				public void run() {
					checkforcommands();
				}
			}, 20, 20);
		}else {
			System.out.println("Edit the config file and reload the server!");
		}
		if(!cfg.contains("slots")){
			cfg.set("slots", Bukkit.getMaxPlayers());
			try {
				cfg.save(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.getCommand("control").setExecutor(new control(this));
		this.getCommand("item").setExecutor(new item(this));
		this.getCommand("inv").setExecutor(new inv(this));
		this.getCommand("serverram").setExecutor(new serverram(this));
		this.getCommand("serverinfo").setExecutor(new serverinfo(this));
		this.getCommand("worlds").setExecutor(new worlds(this));
		this.getCommand("setlife").setExecutor(new setlife(this));
		this.getCommand("overtake").setExecutor(new overtake(this));
		this.getCommand("addplugin").setExecutor(new addplugin(this));
	//	this.getCommand("setmaxusers").setExecutor(new setmaxusers(this));
		
		this.getServer().getPluginManager().registerEvents(new joinlistener(this), this);
		this.getServer().getPluginManager().registerEvents(new movelistener(this), this);
		System.out.println("Controller wurde aktiviert");
	}
	
	protected void checkforcommands() {
		Connection c = null;
		try{
			c=getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `CommandExecutor` WHERE `Server`='"+servername+"' AND `Error`='none' AND `Status`!='yes'");
			if(rs.next()){
				String cmd = rs.getString("Command");
				try{
					doNewLogMetion(rs.getString("Sender") + " executed " + cmd + " on " + rs.getString("Server"), "command");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
					doUpdate(c, "UPDATE `CommandExecutor` SET `Status`='yes' WHERE `Command`='"+cmd+"'");
				}catch(Exception e){
					doUpdate(c, "UPDATE `CommandExecutor` SET `Error`='"+e.getMessage()+"' WHERE `Command`='"+cmd+"'");
				}
			}
		}catch(SQLException x){
			x.printStackTrace();
		}finally {
			releaseConnection(c);
		}
		
	}

	public boolean startCore(){
    	connectionString = "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database;
    	try
        {
          Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException cnfx)
        {
          return false;
        }
    	return true;
    }
 
	private Connection getConnection() throws SQLException {
		Connection c = null;
		c = DriverManager.getConnection(this.connectionString, this.userid, this.password);
		return c;
	}
	
	private void doUpdate(Connection c, String sql) throws SQLException {
		    Statement stmt = c.createStatement();
		    stmt.executeUpdate(sql);
		    stmt.close();
	}
	private void releaseConnection(Connection c){
		try {
			c.close();
		} catch (SQLException x) {
		}
	}
	public void doNewLogMetion(String message, String shortcode){
		Connection c = null;
		String time = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
		int warn = 0;
		switch (shortcode) {
		case "joinTRY": warn = 1; break;
		case "gpermremove": warn = 1; break;
		case "gpermadd": warn = 1; break;
		case "bpermremove": warn = 1; break;
		case "bpermadd": warn = 1; break;
		case "kick": warn = 1; break;
		default: warn = 0; break;
		}
		try {
			c = getConnection();
			doUpdate(c, "INSERT INTO `"+prafix+"Bungee`(`Time`, `Value`, `Shortcode`, `Warning`) VALUES ('"+time+"', '"+message+"', '"+shortcode+"', '"+warn+"')");
		}catch(SQLException x){
		}finally{
			releaseConnection(c);
		}
	}
	
	public void onDisable(){
		System.out.println("Controller wurde deaktiviert");
	}
	
	public Rank getRank(UUID uuid){
		Connection c = null;
		Rank out = Rank.NOTSET;
		try{
			c = getConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `Players` WHERE `UUID`='"+uuid+"'");
			if(rs.next()){
				String rank = rs.getString("Rank");
				switch (rank) {
				default: out = Rank.NOTSET; break;
				case "OPERATOR": out = Rank.OPERATOR; break;
				case "GUEST": out = Rank.GUEST; break;
				case "BANNED": out = Rank.BANNED; break;
				}
			}
		}catch(SQLException x){
			x.printStackTrace();
		}finally{
			releaseConnection(c);
		}
		return out;
	}
	
}
