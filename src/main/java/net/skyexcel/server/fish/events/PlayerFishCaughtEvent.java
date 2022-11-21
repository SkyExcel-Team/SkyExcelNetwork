package net.skyexcel.server.fish.events;

import net.skyexcel.server.fish.data.FishType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerFishCaughtEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private FishType fishType;


    private Player player;

    public PlayerFishCaughtEvent(Player player, FishType fishType) {
        this.fishType = fishType;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public FishType getFishType() {
        return fishType;
    }

    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
