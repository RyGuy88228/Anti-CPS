package us.ryguy.anticps.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.ryguy.anticps.events.PlayerLeftClick;
import us.ryguy.anticps.events.PlayerRightClick;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            PlayerLeftClick plc = new PlayerLeftClick(e.getPlayer());
            if(Bukkit.getPluginManager().getPlugin("Anti-CPS").getConfig().getConfigurationSection("Options").getBoolean("MultiThread")) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("Anti-CPS"), () -> Bukkit.getPluginManager().callEvent(plc));
            }else {
                Bukkit.getPluginManager().callEvent(plc);
            }
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            PlayerRightClick prc = new PlayerRightClick(e.getPlayer());
            if(Bukkit.getPluginManager().getPlugin("Anti-CPS").getConfig().getConfigurationSection("Options").getBoolean("MultiThread")) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("Anti-CPS"), () -> Bukkit.getPluginManager().callEvent(prc));
            }else {
                Bukkit.getPluginManager().callEvent(prc);
            }
        }
    }
}
