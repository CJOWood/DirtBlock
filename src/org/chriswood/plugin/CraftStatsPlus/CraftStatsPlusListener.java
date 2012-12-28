package org.chriswood.plugin.CraftStatsPlus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class CraftStatsPlusListener implements Listener{
	
	private CraftStatsPlusHolder stats;
	private CraftStatsPlusConfig config;
	
	public CraftStatsPlusListener(CraftStatsPlusHolder stats, CraftStatsPlusConfig config) {
		this.stats = stats;
		this.config = config;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event){
		if(!config.getBlockPlace()) return;
		if(event.isCancelled()) return;
		stats.addData("Block Place", event.getPlayer().getName(), ""+event.getBlockPlaced().getTypeId(), 1);
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event){
		if(!config.getBlockBreak()) return;
		if(event.isCancelled()) return;
		stats.addData("Block Break", event.getPlayer().getName(), ""+event.getBlock().getTypeId(), 1);
	}
	

	@EventHandler(priority=EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event){
		if(!config.getKills()) return;
		if(event.getEntity().getKiller() instanceof Player)
		{
			String killee = event.getEntity().toString();
			if(killee.contains("CraftPlayer"))
				stats.addData("Kill", event.getEntity().getKiller().getName(), killee.substring(17, killee.length()-1), 1);
			else
				stats.addData("Kill", event.getEntity().getKiller().getName(), killee, 1);
		}
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerDeath (PlayerDeathEvent event){
		if(!config.getDeaths()) return;
        Player p = event.getEntity();
        EntityDamageEvent damageEvent = p.getLastDamageCause();
        if(damageEvent instanceof EntityDamageByEntityEvent){
        	String damager = ((EntityDamageByEntityEvent)damageEvent).getDamager().toString();
        	if(damager.contains("CraftPlayer")){
        		stats.addData("Death", p.getName(), damager.substring(17, damager.length()-1), 1);
        	}else
        		stats.addData("Death", p.getName(), damager, 1);
        }else
        	stats.addData("Death", p.getName(), damageEvent.getCause().toString(), 1);
    }
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onGainedXp(PlayerExpChangeEvent event){
		if(!config.getXPGained()) return;
		if(event.getAmount() > 0 )
		{
			stats.addData("XP Gained", event.getPlayer().getName(), "XP Increase", event.getAmount());
		}
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onItemBreak(PlayerItemBreakEvent event){
		if(!config.getBrokenItems()) return;
		stats.addData("Item Break", event.getPlayer().getName(), ""+event.getBrokenItem().getTypeId(), 1);
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onBowShot(EntityShootBowEvent event){
		if(!config.getBowShots()) return;
		if(event.isCancelled()) return;
		if(!(event.getEntity() instanceof Player)) return;
		Player p = (Player)event.getEntity();
		stats.addData("Bow Shot", p.getName(), "Bow Shot", 1);
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onItemCraft(CraftItemEvent event){
		if(!config.getCraftedItems()) return;
		if(event.isCancelled()) return;
		Player p = (Player)event.getWhoClicked();
		stats.addData("Item Crafted", p.getName(), ""+event.getCurrentItem().getTypeId(), 1);
	}
}
