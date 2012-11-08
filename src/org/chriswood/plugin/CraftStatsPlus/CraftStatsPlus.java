/*
 * Big thanks to the Votifier plugin for being open source.
 * Oh, and the Internet. Its pretty awesome.
 */

package org.chriswood.plugin.CraftStatsPlus;


import java.util.logging.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftStatsPlus extends JavaPlugin {
	
	private static final Logger LOG = Logger.getLogger("CraftStats+");
	private CraftStatsPlusHolder statData = new CraftStatsPlusHolder();
	private CraftStatsPlusTalk talker;
	
	public void onEnable(){
		CraftStatsPlusConfig config = new CraftStatsPlusConfig(this);
		getServer().getPluginManager().registerEvents(new CraftStatsPlusListener(statData, config), this);
		
		//Initialize SocketTalker...
		String host = config.getHost();
		int port = config.getPort();
		LOG.info("[CraftStats+] Starting on " + host + ":" + port + ".");
		try {
				talker = new CraftStatsPlusTalk(statData, host, port);
				talker.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addData(String event, String user, String data, int dataAmt)
	{
		statData.addData(event, user, data, dataAmt);
	}
	
	public void onDisable(){
		if(talker != null)
			talker.shutdown();
	}
}
