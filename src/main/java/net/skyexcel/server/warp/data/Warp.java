package net.skyexcel.server.warp.data;


import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class Warp {

    private String name;

    private Location location;

    private Config config;

    public Warp(String name) {
        this.name = name;
        this.config = new Config("warp/warp");
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void setLocation(Player player) {
        config.setLocation("warp." + name, player.getLocation());
    }

    public void deleteLocation() {
        config.getConfig().set("warp." + name, "");
        config.saveConfig();
    }


    public Location getLocation() {
        return config.getLocation("warp." + name);
    }

}
