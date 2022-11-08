package net.skyexcel.server.skyblock.data.event;

import net.skyexcel.server.skyblock.data.island.SkyBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class SkyBlockQuickEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    private String name;

    private SkyBlock islandData;

    private Player player;

    private CancelCause cancelCause;

    private QuickCuase cause;


    public SkyBlockQuickEvent(String name, SkyBlock islandData, Player player) {
        this.name = name;
        this.islandData = islandData;
        this.player = player;
        this.isCancelled = false;
    }

    public void setCause(QuickCuase cause) {
        this.cause = cause;
    }

    public QuickCuase getCause() {
        return cause;
    }

    public void setCancelCause(CancelCause cancelCause) {
        this.cancelCause = cancelCause;
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
     * 입장한 섬의 이름을 불러옵니다.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public SkyBlock getIslandData() {
        return islandData;
    }

    public CancelCause getCancelCause() {
        return cancelCause;
    }

    public Player getPlayer() {
        return player;
    }

    public enum CancelCause {
        OWNER(), NOT_MEMBER;

    }

    public enum QuickCuase{
        SERVER, ISLAND
    }


}
