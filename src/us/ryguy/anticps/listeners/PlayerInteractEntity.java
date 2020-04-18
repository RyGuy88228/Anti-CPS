package us.ryguy.anticps.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import us.ryguy.anticps.events.PlayerRightClick;

public class PlayerInteractEntity implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractAtEntityEvent e) {
        PlayerRightClick prc = new PlayerRightClick(e.getPlayer());
        if(Bukkit.getPluginManager().getPlugin("Anti-CPS").getConfig().getConfigurationSection("Options").getBoolean("MultiThread")) {
            Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("Anti-CPS"), () -> Bukkit.getPluginManager().callEvent(prc));
        }else {
            Bukkit.getPluginManager().callEvent(prc);
        }
    }
}
