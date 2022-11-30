package net.skyexcel.server.afkregion.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import skyexcel.data.Time;

public class AFKDetectData {
    @Getter
    @Setter
    private long lastInteracted;

    @Getter
    @Setter
    private long lastMoved;

    private Player player;


    private Time time;

    public AFKDetectData(Player player) {
        this.player = player;
        this.time = new Time(lastMoved);
    }
}
