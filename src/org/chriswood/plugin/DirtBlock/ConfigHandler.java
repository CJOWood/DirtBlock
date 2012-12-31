package org.chriswood.plugin.DirtBlock;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ConfigHandler {
	
	public static void toggleBoolean(String path, CommandSender target) {
		
		boolean bool = DirtBlock.db.getConfig().getBoolean(path);
				
		if (bool == true) {
			
			DirtBlock.db.getConfig().set(path, false);
			DirtBlock.db.saveConfig();
			
			target.sendMessage(ChatColor.DARK_GRAY + "DirtBlock: " + ChatColor.GREEN + "Server is no longer tracking " + path + " for CraftStats.");
			
		}
		
		else {
			
			DirtBlock.db.getConfig().set(path, true);
			DirtBlock.db.saveConfig();
			
			target.sendMessage(ChatColor.DARK_GRAY + "DirtBlock: " + ChatColor.GREEN + "Server is now tracking " + path + " for CraftStats.");
			
		}
		
	}
	
	public static void reload() {
		
		DirtBlock.db.reloadConfig();
		
	}

}
