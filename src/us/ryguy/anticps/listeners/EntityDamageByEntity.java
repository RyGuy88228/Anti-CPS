package us.ryguy.anticps.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import us.ryguy.anticps.events.PlayerLeftClick;

public class EntityDamageByEntity implements Listener {
        @EventHandler
        public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
            if(e.getDamager().getType() == EntityType.PLAYER) {
                PlayerLeftClick plc = new PlayerLeftClick((Player) e.getDamager());
                if(Bukkit.getPluginManager().getPlugin("Anti-CPS").getConfig().getConfigurationSection("Options").getBoolean("MultiThread")) {
                    Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("Anti-CPS"), () -> Bukkit.getPluginManager().callEvent(plc));
                }else {
                    Bukkit.getPluginManager().callEvent(plc);
                }
            }
        }
}
