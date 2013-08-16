package io.github.DJR1996.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class iGriefListener implements Listener {
	
	FileConfiguration config = iGrief.config;
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event){
		if(event.isCancelled()) return;
		Material mat = event.getBlock().getType();
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		if(!player.hasPermission("igrief.bypass") && ((mat == Material.TNT && config.getBoolean("disableTNT"))
				|| (mat == Material.OBSIDIAN && config.getBoolean("disableObsidian"))
				|| (mat == Material.BEDROCK && config.getBoolean("disableBedrock"))
				|| (mat == Material.FIRE && config.getBoolean("disableFire")))){
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You don't have permission to place that");
			for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
				if(onlinePlayer.hasPermission("igrief.notify")){
					onlinePlayer.sendMessage(ChatColor.RED + player.getName() + ("just tried to use a banned item at X:"
							+ loc.getBlockX() + " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ()));
				}
			}
		}
		
		if (config.getIntegerList("otherBlocksToDisable").contains(mat.getId())){
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You don't have permission to place that");
			for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
				if(onlinePlayer.hasPermission("igrief.notify")){
					onlinePlayer.sendMessage(ChatColor.RED + player.getName() + ("just tried to use a banned item at X:"
							+ loc.getBlockX() + " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ()));
				}
			}
		}
	}
		
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event){
		if (event.isCancelled()) return;
		if (event.getItem() == null) return;
		Material mat = event.getItem().getType();
		Player player = event.getPlayer();
		Location loc = event.getClickedBlock().getLocation();
		if(!player.hasPermission("igrief.bypass") && ((mat == Material.FLINT_AND_STEEL && config.getBoolean("disableFlintandsteel")) 
				|| (mat == Material.LAVA_BUCKET && config.getBoolean("disableLavaBucket"))
				|| (mat == Material.FIREBALL && config.getBoolean("disableFireball"))
				|| (mat == Material.MONSTER_EGGS && event.getItem().getDurability() == 383))){
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You don't have permission to place that");
			for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
				if(onlinePlayer.hasPermission("igrief.notify")){
					onlinePlayer.sendMessage(ChatColor.RED + player.getName() + ("just tried to use a banned item at X:"
							+ loc.getBlockX() + " Y:" + loc.getBlockY() + " Z:" + loc.getBlockZ()));
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onExplosionPrime(ExplosionPrimeEvent event){
		if(event.isCancelled()) return;
		if (config.getBoolean("disableTNTExplosion") && event.getEntity() instanceof TNTPrimed){
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityExplode(EntityExplodeEvent event){
		if(event.isCancelled()) return;
		if(config.getBoolean("disableCreeperExplosion")){
			if(event.getEntity() instanceof Creeper){
				event.setCancelled(true);
			}
		}
		
		if(config.getBoolean("disableFireball")){
			if(event.getEntity() instanceof Fireball){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void  onBlockIgniteEvent(BlockIgniteEvent event){
		if(event.isCancelled()) return;
		if(config.getBoolean("disableBlockIgnite")){
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityByEntityDamage(EntityDamageByEntityEvent event){
		if(event.isCancelled()) return;
		if (event.getCause() == DamageCause.ENTITY_EXPLOSION){
			Entity entity = event.getDamager();
			if((entity instanceof TNTPrimed && config.getBoolean("disableTNTDamage")) 
					|| (entity instanceof Creeper && config.getBoolean("disableCreeperDamage"))
					|| (entity instanceof Fireball && config.getBoolean("disableFireball"))){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBurn(BlockBurnEvent event){
		if(event.isCancelled()) return;
		if(config.getBoolean("disableBurning")){
			event.setCancelled(true);
		}
	}
}
