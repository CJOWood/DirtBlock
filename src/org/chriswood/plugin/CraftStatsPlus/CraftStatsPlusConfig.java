package org.chriswood.plugin.CraftStatsPlus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class CraftStatsPlusConfig {
	private static final Logger LOG = Logger.getLogger("CraftStats+");
	private CraftStatsPlus CSP;
	private File config;
	private YamlConfiguration cfg;
	
	public CraftStatsPlusConfig(CraftStatsPlus CSP) {
		this.CSP = CSP;
		setupConfig();
		loadConfig();
	}
	
	public void setupConfig() {
		//Create/Load configuration stuff...
		if (!CSP.getDataFolder().exists()) {
			CSP.getDataFolder().mkdir();
		}
		this.config = new File(CSP.getDataFolder(), "config.yml");
		
		if(!config.exists())
			getDefaultConfig(CSP.getResource("config.yml"), config);
	}
	
	public void loadConfig() {
		cfg = YamlConfiguration.loadConfiguration(config);
		
		//Check Properties
		if(cfg.getBoolean("check-query"))
			checkProp();
	}
	
	private void checkProp()
	{
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("server.properties"));
			
            if(prop.getProperty("enable-query").equals("true") && Bukkit.getIp().toString().equals(""))
            	LOG.warning("\"server-ip\" in server.properties is not set!");
            else if(prop.getProperty("enable-query").equals("false") && !Bukkit.getIp().toString().equals(""))
            	LOG.warning("\"enable-query\" in server.properties is set to false!");
            else
            {
            	LOG.warning("\"enable-query\" in server.properties is set to false!");
            	LOG.warning("\"server-ip\" in server.properties is not set!");
            }
	 	} catch (IOException ex) {
	 		ex.printStackTrace();
	    }
	}
	
	private void getDefaultConfig(InputStream in, File file) {
		try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public String getHost() {
		return (String) cfg.get("host", "0.0.0.0");
	}
	
	public int getPort() {
		return cfg.getInt("port", 22565);
	}
	
	public boolean getUsersOnline() {
		return cfg.getBoolean("UsersOnline", true);
	}
	
	public boolean getPastUsersOnline() {
		return cfg.getBoolean("PastUsersOnline", true);
	}
	
	public boolean getBlockBreak() {
		return cfg.getBoolean("BlockBreak", true);
	}
	
	public boolean getBlockPlace() {
		return cfg.getBoolean("BlockPlace", true);
	}
	
	public boolean getKills() {
		return cfg.getBoolean("Kills", true);
	}
	
	public boolean getDeaths() {
		return cfg.getBoolean("Deaths", true);
	}
	
	public boolean getBowShots() {
		return cfg.getBoolean("BowShot", true);
	}
	
	public boolean getCraftedItems() {
		return cfg.getBoolean("CraftedItems", true);
	}
	
	public boolean getBrokenItems() {
		return cfg.getBoolean("BrokenItems", true);
	}
	
	public boolean getXPGained() {
		return cfg.getBoolean("XPGained", true);
	}
}
