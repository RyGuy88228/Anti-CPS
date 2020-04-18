package us.ryguy.anticps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import us.ryguy.anticps.cmd.AntiCPS;
import us.ryguy.anticps.cmd.TabComplete;
import us.ryguy.anticps.listeners.*;
import us.ryguy.anticps.util.Stopwatch;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    public HashMap<UUID, Stopwatch> leftClicks = new HashMap<>();
    public HashMap<UUID, Stopwatch> rightClicks = new HashMap<>();
    public void onEnable() {
        //Config
        if(getConfig().getConfigurationSection("Options") == null) {
            getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[XPMiner] Configuration Initializing!");
            initConfiguration();
        }
        //Events and Command
        getCommand("anticps").setExecutor(new AntiCPS());
        getCommand("anticps").setTabCompleter(new TabComplete());
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new onLeftClick(), this);
        getServer().getPluginManager().registerEvents(new onRightClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Anti-CPS]: Events registered and plugin initialized!");
    }
    public void onDisable() {
        saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Anti-CPS]: Config Saved and Anti-CPS disabled!");
    }
    public void initConfiguration() {
        getConfig().createSection("Options");
        getConfig().getConfigurationSection("Options").addDefault("LeftCPSMax", 10);
        getConfig().getConfigurationSection("Options").addDefault("RightCPSMax", 10);
        getConfig().getConfigurationSection("Options").addDefault("ConsoleLogging", true);
        getConfig().getConfigurationSection("Options").addDefault("MultiThread", true);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public void checkConfig() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Anti-CPS] Checking your Configuration File!");
        for(Object o : getConfig().getConfigurationSection("Options").getKeys(false)) {
            if(((String) o).equalsIgnoreCase("ConsoleLogging")) return;
            Object val = getConfig().getConfigurationSection("Options").get((String)o);
            if(!isInteger((String) o)) {
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Anti-CPS] Option " + (String)o + " was not set to an integer! Setting to a default number of 10!");
                getConfig().getConfigurationSection("Options").set((String)o, 10);
                saveConfig();
            }
            if(!(val instanceof Integer)) {
                getConfig().getConfigurationSection("Options").set((String)o, (int) val);
                saveConfig();
            }
        }
    }
    public HashMap<UUID, Stopwatch> getLeftClicks() {
        return this.leftClicks;
    }
    public HashMap<UUID, Stopwatch> getRightClicks() {
        return this.rightClicks;
    }
    public boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException e) {
            //
        }
        return isValidInteger;
    }
}
