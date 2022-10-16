package net.skyexcel.server.data.event;

import net.skyexcel.server.data.island.IslandData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class IslandJoinEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    private String name;

    private IslandData islandData;


    private Player player;

    public IslandJoinEvent(String name, IslandData islandData, Player player) {
        this.name = name;
        this.islandData = islandData;
        this.player = player;


        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public IslandData getIslandData() {
        return islandData;
    }

    /**
     * 입장한 섬의 이름을 불러옵니다.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }
}
