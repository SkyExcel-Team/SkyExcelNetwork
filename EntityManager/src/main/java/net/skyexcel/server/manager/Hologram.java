package net.skyexcel.server.manager;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class Hologram {

    private Config config;

    public Hologram(Config config) {
        this.config = config;
    }


    public void spawn(Player player, Location location, String name) {
        ArmorStand armorStand = player.getWorld().spawn(location, ArmorStand.class);
        armorStand.setCustomName(name);
        config.setLocation("armor", location);
    }


}
