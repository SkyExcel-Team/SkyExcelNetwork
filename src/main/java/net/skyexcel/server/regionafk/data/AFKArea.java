package net.skyexcel.server.regionafk.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AFKArea {

    private Player player;

    private Location pos1;

    private Location pos2;

    public AFKArea(Player player) {
        this.player = player;
    }


    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }
}
