package net.skyexcel.server.skyblock.data.event;

import net.skyexcel.server.skyblock.data.island.SkyBlock;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class SkyBlockJoinEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    private String name;

    private SkyBlock islandData;


    private Player player;

    private OfflinePlayer target;

    private JoinCause joinCause;

    private CancelCause cancelCause = CancelCause.DEFAULT;

    public SkyBlockJoinEvent(String name, SkyBlock islandData, Player player, OfflinePlayer target) {
        this.name = name;
        this.islandData = islandData;
        this.player = player;
        this.target = target;
        this.isCancelled = false;
    }

    public void setJoinCause(JoinCause joinCause) {
        this.joinCause = joinCause;
    }


    public JoinCause getJoinCause() {
        return joinCause;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }


    public void setCancelCause(CancelCause cancelCause) {
        this.cancelCause = cancelCause;
    }

    public CancelCause getCancelCause() {
        return cancelCause;
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

    public SkyBlock getIslandData() {
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


    public enum CancelCause {
        NONE, LOCK, DEFAULT
    }

    public enum JoinCause {
        VISIT(), MEMBER(), ISLAND, OWNER;
    }

}
