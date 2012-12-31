package org.chriswood.plugin.DirtBlock;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
	
	private DirtBlock plugin;
	public CommandHandler(DirtBlock db)
	{
		this.plugin = db;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 0) {
			
			sender.sendMessage(ChatColor.DARK_GRAY + "DirtBlock: " + ChatColor.RED + "Incorrect syntax, try: /db help");
			
		}
		
		else if (args[0].equalsIgnoreCase("help")) {
			
			if (sender.isOp() || sender.hasPermission("dirtblock.help")) {
			
				sender.sendMessage(ChatColor.DARK_GRAY + "<------------- DirtBlock Help ------------->");
				sender.sendMessage(ChatColor.DARK_GRAY + "/db help - View the help pane for DirtBlock.");
				sender.sendMessage(ChatColor.DARK_GRAY + "/db toggle <setting> - Toggle any of the CraftStats trackers.");
				
			}
			
			else {
				
				sender.sendMessage(ChatColor.DARK_GRAY + "DirtBlock: " + ChatColor.RED + "Sorry, you have insufficient permissions to perform that action.");
				
			}
			
		}
		
		else if (args[0].equalsIgnoreCase("toggle")) {
			
			if (sender.isOp() || sender.hasPermission("dirtblock.toggle")) {
			
				if (args[1].equalsIgnoreCase("usersonline")) {
				
					ConfigHandler.toggleBoolean("UsersOnline", sender);
							
				}
			
				else if (args[1].equalsIgnoreCase("pastusersonline")) {
				
					ConfigHandler.toggleBoolean("PastUsersOnline", sender);
				
				}
			
				else if (args[1].equalsIgnoreCase("blockbreak")) {
				
					ConfigHandler.toggleBoolean("BlockBreak", sender);
				
				}
			
				else if (args[1].equalsIgnoreCase("blockplace")) {
				
					ConfigHandler.toggleBoolean("BlockPlace", sender);
				
				}
			
				else if (args[1].equalsIgnoreCase("kills")) {
				
					ConfigHandler.toggleBoolean("Kills", sender);
					
				}
			
				else if (args[1].equalsIgnoreCase("deaths")) {
				
					ConfigHandler.toggleBoolean("Deaths", sender);
				
				}
			
				else if (args[1].equalsIgnoreCase("bowshots")) {
				
					ConfigHandler.toggleBoolean("BowShots", sender);
				
				}
			
				else if (args[1].equalsIgnoreCase("crafteditems")) {
				
					ConfigHandler.toggleBoolean("CraftedItems", sender);
				
				}
			
				else if (args[1].equalsIgnoreCase("brokenitems")) {
				
					ConfigHandler.toggleBoolean("BrokenItems", sender);
					
				}
			
				else if (args[1].equalsIgnoreCase("xpgained")) {
				
					ConfigHandler.toggleBoolean("XPGained", sender);
				
				}
				
				else if (args[1].equalsIgnoreCase("updatecheck")) {
					
					ConfigHandler.toggleBoolean("UpdateCheck", sender);
					
				}
				
			}
			
			else {
				
				sender.sendMessage(ChatColor.DARK_GRAY + "DirtBlock: " + ChatColor.RED + "Sorry, you have insufficient permissions to perform that action.");
				
			}
						
		}
		
		else {
			
			sender.sendMessage(ChatColor.DARK_GRAY + "DirtBlock: " + ChatColor.RED + "Incorrect syntax, try: /db help");
			
		}
		
		return true;
		
	}
}