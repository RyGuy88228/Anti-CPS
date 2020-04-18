package us.ryguy.anticps.events;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRightClick extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public PlayerRightClick(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return this.player;
    }
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
