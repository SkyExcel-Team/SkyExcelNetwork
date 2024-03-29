package net.skyexcel.server.warp.data;


import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class Warp {

    private String name;

    private Location location;

    private Config config;


    public Warp() {
        this.config = new Config("warp/warp");
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public Warp(String name) {
        this.name = name;
        this.config = new Config("warp/warp");
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void setLocation(Player player) {
        config.setLocation("warp." + name, player.getLocation());
    }

    public void deleteLocation() {
        config.removeKey("warp." + name);

        config.saveConfig();
    }

    public List<String> getList() {
        ConfigurationSection section = config.getConfig().getConfigurationSection("warp");
        if (section != null)
            return new ArrayList<>(section.getKeys(false));
        return new ArrayList<>();
    }

    public void teleport(Player player) {
        if (location != null) {
            player.teleport(location);
            player.sendMessage("家 " + name + "(으)로 이동하였습니다!");
            player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        } else {
            player.sendMessage("强 §c워프가 존재하지 않습니다.");
        }
    }


    public Config getConfig() {
        return config;
    }

    public Location getLocation() {
        return config.getLocation("warp." + name);
    }
}
