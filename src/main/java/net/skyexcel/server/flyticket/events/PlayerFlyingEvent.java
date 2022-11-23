package net.skyexcel.server.flyticket.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerFlyingEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Player player;

    private Flying flying;


    public PlayerFlyingEvent(Player player, Flying flying) {
        this.player = player;
        this.flying = flying;
    }


    public Player getPlayer() {
        return player;
    }

    public Flying getFlying() {
        return flying;
    }

    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public static enum Flying {
        FLYING, GROUND
    }
}
