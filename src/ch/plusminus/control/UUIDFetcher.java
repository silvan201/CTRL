package ch.plusminus.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class UUIDFetcher {

	public static UUID getUUID(String p) {
		UUID out = null;
		JsonParser parser = new JsonParser();
		download("player.uuid", "https://api.mojang.com/users/profiles/minecraft/"+p);
		Object obj = null;
		try {
			obj = parser.parse(new FileReader("player.uuid"));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
		JsonObject jsonObj = (JsonObject) obj;
		
		String id = (String) jsonObj.get("id").toString();
		out = UUID.fromString(id.substring(1, 9) + "-" + id.substring(9, 13) + "-" + id.substring(13, 17) + "-" + id.substring(17, 21) + "-" +id.substring(21, 33));
		return out;
	}
	
	private static void download(String toname, String url){
		    try
		    {
		  	  {
		  	    URL URL = new URL(url);
		  	    URLConnection uc = URL.openConnection();
		  	    BufferedReader ein = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		  	    boolean b = true;
		  	    File downloadedfile = new File(toname);
		  	    if (downloadedfile.exists()) {
		  	      downloadedfile.delete();
		  	    }
		  	    downloadedfile.createNewFile();
		  	    FileWriter fw = new FileWriter(downloadedfile);
		  	    while (b)
		  	    {
		  	      String Zeile = ein.readLine();
		  	      if (Zeile == null)
		  	      {
		  	        b = false;
		  	        fw.close();
		  	        break;
		  	      }
		  	      fw.write(Zeile);
		  	      fw.append(System.getProperty("line.separator"));
		  	    }
		  	  }
		    }
		    catch (IOException e)
		    {
		      e.printStackTrace();
			}
	}
}
