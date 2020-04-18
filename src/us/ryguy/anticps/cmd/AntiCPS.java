package us.ryguy.anticps.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.ryguy.anticps.Main;

public class AntiCPS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Main main = (Main) Bukkit.getPluginManager().getPlugin("Anti-CPS");
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("reload")) {
                    if(sender.hasPermission("anticps.reload") || sender.hasPermission("anticps.op")) {
                        try {
                            main.reloadConfig();
                            main.checkConfig();
                            sender.sendMessage(net.md_5.bungee.api.ChatColor.DARK_AQUA + "[Anti-CPS]" + ChatColor.GRAY + " Config Reloaded!");
                        } catch (Exception e) {
                            sender.sendMessage(net.md_5.bungee.api.ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "There was a(n) " + e.getClass().getSimpleName() + " in attempting to reload configuration!");
                            sender.sendMessage(net.md_5.bungee.api.ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + e.getMessage());
                            sender.sendMessage(net.md_5.bungee.api.ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + e.getLocalizedMessage());
                            sender.sendMessage(net.md_5.bungee.api.ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "Try to use a rich text editor to edit your configuration, and if that shows nothing, and you believe it is a bug, report it!");
                    }
                }else sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "No Permission!");
            }else if(args[0].equalsIgnoreCase("getvalue")) {
                if(sender.hasPermission("anticps.getvalue") || sender.hasPermission("anticps.op")) {
                    if(args.length == 2) {
                        if(args[1].equalsIgnoreCase("leftclick")) {
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "The maximum " + ChatColor.GOLD + "left-click" + ChatColor.GRAY + " speed is currently " + ChatColor.GOLD + main.getConfig().getConfigurationSection("Options").getString("LeftCPSMax") + " CPS!");
                        }else if(args[1].equalsIgnoreCase("rightclick")) {
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "The maximum " + ChatColor.GOLD + "right-click" + ChatColor.GRAY + " speed is currently " + ChatColor.GOLD + main.getConfig().getConfigurationSection("Options").getString("RightCPSMax") + " CPS!");
                        }else {
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "Improper Usage!");
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "Example: /anticps getvalue leftclick");
                        }
                    }else {
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "Improper Usage!");
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "Example: /anticps getvalue leftclick");
                    }
                }else sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "No Permission!");
            }else if(args[0].equalsIgnoreCase("setvalue")) {
                if(sender.hasPermission("anticps.setvalue") || sender.hasPermission("anticps.op")) {
                    if(args.length == 3) {
                        if (args[1].equalsIgnoreCase("leftclick")) {
                            main.getConfig().getConfigurationSection("Options").set("LeftCPSMax", Integer.valueOf(args[2]));
                            main.saveConfig();
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "The maximum " + ChatColor.GOLD + "left-click" + ChatColor.GRAY + " is now set to " + ChatColor.GOLD + main.getConfig().getConfigurationSection("Options").getString("LeftCPSMax") + " CPS!");
                        } else if (args[1].equalsIgnoreCase("rightclick")) {
                            main.getConfig().getConfigurationSection("Options").set("RightCPSMax", Integer.valueOf(args[2]));
                            main.saveConfig();
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "The maximum " + ChatColor.GOLD + "right-click" + ChatColor.GRAY + " is now set to " + ChatColor.GOLD + main.getConfig().getConfigurationSection("Options").getString("RightCPSMax") + " CPS!");
                        } else {
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "Improper Usage!");
                            sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "Example: /anticps getvalue leftclick");
                        }
                    }else {
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "Improper Usage!");
                        sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "Example: /anticps getvalue leftclick");
                    }
                }else sender.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.RED + "No Permission!");
            }else {
                sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "---" + ChatColor.RESET.toString() + ChatColor.BOLD + "" + ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + " Anti-CPS Help " + ChatColor.GRAY + "]" + ChatColor.STRIKETHROUGH + "---");
                sender.sendMessage(ChatColor.DARK_AQUA + "/anticps reload" + ChatColor.GRAY + " - Reload's AntiCPS's Config!");
                sender.sendMessage(ChatColor.DARK_AQUA + "/anticps getvalue" + ChatColor.GRAY + " - Gets the value of the maximum cps for either left or right click!");
                sender.sendMessage(ChatColor.DARK_AQUA + "/anticps setvalue" + ChatColor.GRAY + " - Sets the value of the maximum cps for either left or right click!");
            }
        }else {
            sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "---" + ChatColor.RESET.toString() + ChatColor.BOLD + "" + ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + " Anti-CPS Help " + ChatColor.GRAY + "]" + ChatColor.STRIKETHROUGH + "---");
            sender.sendMessage(ChatColor.DARK_AQUA + "/anticps reload" + ChatColor.GRAY + " - Reload's AntiCPS's Config!");
            sender.sendMessage(ChatColor.DARK_AQUA + "/anticps getvalue" + ChatColor.GRAY + " - Gets the value of the maximum cps for either left or right click!");
            sender.sendMessage(ChatColor.DARK_AQUA + "/anticps setvalue" + ChatColor.GRAY + " - Sets the value of the maximum cps for either left or right click!");
        }
        return true;
    }
}
