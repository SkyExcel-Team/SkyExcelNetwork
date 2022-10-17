package net.skyexcel.server.data.player;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SkyBlockPlayerData {


    private Config config;

    private Player player;


    public SkyBlockPlayerData(Player player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void setName(String name) {
        config.getConfig().set("island.name", name);
        config.saveConfig();
    }

    public boolean setSpawn() {
        if (config != null) {
            config.setLocation("island.spawn", player.getLocation());
            config.saveConfig();
            return true;
        }
        return false;
    }

    public boolean hasIsland() {
        return config.getConfig().get("island.name") != null;
    }

    public String getIsland() {
        return config.getConfig().getString("island.name");
    }
}
