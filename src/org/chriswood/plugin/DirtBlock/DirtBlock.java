/*
 * Big thanks to the Votifier plugin for being open source.
 * Oh, and the Internet. It's pretty awesome.
 */

package org.chriswood.plugin.DirtBlock;

import java.util.logging.*;

import org.bukkit.plugin.java.JavaPlugin;

public class DirtBlock extends JavaPlugin {
	
	public static DirtBlock db;

	public DirtBlock() {
		
		db = this;
	
	}
	
	private static final Logger LOG = Logger.getLogger("DirtBlock");
	private DirtBlockHolder statData = new DirtBlockHolder();
	private DirtBlockTalk talker;
	
	public void onEnable() {
		DirtBlockConfig config = new DirtBlockConfig(this);
		getServer().getPluginManager().registerEvents(new DirtBlockListener(statData, config), this);
		
		getCommand("db").setExecutor(new CommandHandler(this));
		
		//Initialize Talker...
		String host = config.getHost();
		int port = config.getPort();
		LOG.info("[DirtBlock] Starting on " + host + ":" + port + ".");
		if(host == "0.0.0.0")
			LOG.warning("[DirtBlock] Host is currently set to the default value. Please change this in the DirtBlock config.yml.");
		try {
				talker = new DirtBlockTalk(statData, host, port);
				talker.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addData(String event, String user, String data, Double dataAmt) {
		statData.addData(event, user, data, dataAmt);
	}
	
	public void onDisable(){
		if(talker != null)
			talker.shutdown();
	}
	
}
