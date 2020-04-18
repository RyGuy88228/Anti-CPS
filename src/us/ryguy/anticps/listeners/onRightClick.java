package us.ryguy.anticps.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import us.ryguy.anticps.Main;
import us.ryguy.anticps.events.PlayerRightClick;
import us.ryguy.anticps.util.Stopwatch;

import java.util.HashMap;
import java.util.UUID;

public class onRightClick implements Listener {
    @EventHandler
    public void RightClick(PlayerRightClick e) {
        Main main = (Main) Bukkit.getPluginManager().getPlugin("Anti-CPS");
        HashMap<UUID, Stopwatch> map = main.getRightClicks();
        UUID uuid = e.getPlayer().getUniqueId();
        Stopwatch sw = new Stopwatch();
        double cpsMax = main.getConfig().getConfigurationSection("Options").getDouble("RightCPSMax");
        if(map.get(uuid) != null) {
            Stopwatch resultant = map.get(uuid);
            resultant.stop();
            if(resultant.getTime() > 1000000000) {
                map.remove(uuid);
                return;
            }
            double seconds = (double) resultant.getTime() / 1_000_000_000.0;
            if(seconds < (1/cpsMax)) {
                for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if(p.hasPermission("anticps.check")) {
                        p.sendMessage(ChatColor.DARK_AQUA + "[Anti-CPS] " + ChatColor.GRAY + "Player " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.GRAY + " is right-clicking at " + Math.round(1/seconds) + " CPS!");
                    }
                }
                if(main.getConfig().getConfigurationSection("Options").getBoolean("ConsoleLogging")) {
                    main.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Anti-CPS] Player "  + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.AQUA + " is right-clicking at " + Math.round(1/seconds) + " CPS!");
                }
            }
            map.remove(uuid);
        }else {
            sw.start();
            map.put(uuid, sw);
        }
    }
}
