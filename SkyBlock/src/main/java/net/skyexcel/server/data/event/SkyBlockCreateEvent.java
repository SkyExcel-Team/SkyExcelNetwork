package net.skyexcel.server.data.event;

import net.skyexcel.server.data.island.SkyBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class SkyBlockCreateEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    private String name;

    private SkyBlock islandData;

    private Player player;

    private Type type;


    public SkyBlockCreateEvent(String name, SkyBlock islandData, Player player) {
        this.name = name;
        this.islandData = islandData;
        this.player = player;
        this.isCancelled = false;
    }

    public void setType(Type type) {
        this.type = type;
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

    /**
     * @return 생성한 섬의 이름을 불러옵니다.
     */
    public String getName() {
        return name;
    }

    public SkyBlock getIslandData() {
        return islandData;
    }

    public Player getPlayer() {
        return player;
    }


    public Type getType() {
        return type;
    }

    public enum Type {
        SMALL("작은 섬"), MIDDLE("중간 섬"), LARGE("큰 섬");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
