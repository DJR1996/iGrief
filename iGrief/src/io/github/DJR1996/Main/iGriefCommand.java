package io.github.DJR1996.Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class iGriefCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		
		if(cmdlabel.equalsIgnoreCase("ig")){
			if (args.length == 1 && args[0].equalsIgnoreCase("reload")){
				iGrief.plugin.reloadConfig();
				sender.sendMessage(ChatColor.YELLOW + "iGrief files reloaded");
			} else if (args.length == 2){
				if (args[0].equalsIgnoreCase("toggle")){
					if (!sender.hasPermission("igrief.toggle")){
						sender.sendMessage(ChatColor.RED + "You do not have permission to do that");
						return true;
					}
					
					if (args[1].equalsIgnoreCase("tnt")){
						iGrief.config.set("disableTNT", !iGrief.config.getBoolean("disableTNT"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled tnt to " + iGrief.config.getBoolean("disableTNT"));
					} else if (args[1].equalsIgnoreCase("Obsidian")){
						iGrief.config.set("disableObsidian", !iGrief.config.getBoolean("disableObsidian"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled Obsidian to " + iGrief.config.getBoolean("disableObsidian"));
					} else if (args[1].equalsIgnoreCase("Bedrock")){
						iGrief.config.set("disableBedrock", !iGrief.config.getBoolean("disableBedrock"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled Bedrock to " + iGrief.config.getBoolean("disableBedrock"));
					} else if (args[1].equalsIgnoreCase("Fire")){
						iGrief.config.set("disableFire", !iGrief.config.getBoolean("disableFire"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled Fire to " + iGrief.config.getBoolean("disableFire"));
					} else if (args[1].equalsIgnoreCase("Flintandsteel")){
						iGrief.config.set("disableFlintandsteel", !iGrief.config.getBoolean("disableFlintandsteel"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled Flintandsteel to " + iGrief.config.getBoolean("disableFlintandSteel"));
					} else if (args[1].equalsIgnoreCase("LavaBucket")){
						iGrief.config.set("disableLavaBucket", !iGrief.config.getBoolean("disableLavaBucket"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled LavaBucket to " + iGrief.config.getBoolean("disableLavaBucket"));
					} else if (args[1].equalsIgnoreCase("Fireball")){
						iGrief.config.set("disableFireball", !iGrief.config.getBoolean("disableFireball"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled Fireball to " + iGrief.config.getBoolean("disableFireball"));
					} else if (args[1].equalsIgnoreCase("TNTExplosion")){
						iGrief.config.set("disableTNTExplosion", !iGrief.config.getBoolean("disableTNTExplosion"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled TNTExplosion to " + iGrief.config.getBoolean("disableTNTExplosion"));
					} else if (args[1].equalsIgnoreCase("CreeperExplosion")){
						iGrief.config.set("disableCreeperExplosion", !iGrief.config.getBoolean("disableCreeperExplosion"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled CreeperExplosion to " + iGrief.config.getBoolean("disableCreeperExplosion"));
					} else if (args[1].equalsIgnoreCase("BlockIgnite")){
						iGrief.config.set("disableBlockIgnite", !iGrief.config.getBoolean("disableBlockIgnite"));
						iGrief.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Successfully toggled BlockIgnite to " + iGrief.config.getBoolean("disableBlockIgnite"));
					} else {
						int blockID;
						
						try {
							blockID = Integer.valueOf(args[1]);
						} catch (Exception e){
							sender.sendMessage(ChatColor.RED + "Value must be numeric");
							return true;
						}
						
						List<Integer> toSet = new ArrayList<Integer>();
						
						for (Integer i : iGrief.config.getIntegerList("otherBlocksToDisable")){
							toSet.add(i);
						}
						
						if (toSet.contains(blockID)){
							toSet.remove((Integer) blockID);
							sender.sendMessage(ChatColor.GREEN + "Removed " + blockID + " from the config");
						} else {
							toSet.add(blockID);
							sender.sendMessage(ChatColor.GREEN + "Added " + blockID + " to the config");
						}
						
						iGrief.config.set("otherBlocksToDisable", toSet);
						iGrief.plugin.saveConfig();
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		return true;
	}

}

