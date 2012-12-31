package org.chriswood.plugin.DirtBlock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class DirtBlockConfig {
	private static final Logger LOG = Logger.getLogger("DirtBlock");
	private DirtBlock CSP;
	private File config;
	private YamlConfiguration cfg;
	
	public DirtBlockConfig(DirtBlock CSP) {
		this.CSP = CSP;
		setupConfig();
		loadConfig();
		checkCS();
	}
	
	public void checkCS(){
		if(getContactCS()){
			try{
				/*
				 * Let CraftStats know you are using the plugin so it can ping for stats. This will be changing to a better version soon!
				 * Duals at a Update Check.
				 */
				URL cstats = new URL("http://192.241.15.102/api?req=m07");
				URLConnection conn = cstats.openConnection();
				conn.setReadTimeout(10000);
				conn.connect();
				if(getUpdateCheck())
				{
					BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String returned;
					while((returned = read.readLine()) != null){
						if(getUpdateCheck() && !(returned.equals(CSP.getDescription().getVersion().toString())))
							LOG.info("[DirtBlock] You are not using the lastest version of DirtBlock ("+ returned +")!");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
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
	
	public boolean getContactCS(){
		return cfg.getBoolean("ContactCS", true);
	}
	
	public boolean getUpdateCheck(){
		return cfg.getBoolean("UpdateCheck", true);
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
	
	public boolean getPlayerDmg() {
		return cfg.getBoolean("PlayerDamage", true);
	}
	
	public boolean getPlayerMove() {
		return cfg.getBoolean("PlayerMove", true);
	}
	
	public boolean getBETA(){
		return cfg.getBoolean("BETA", false);
	}
}
