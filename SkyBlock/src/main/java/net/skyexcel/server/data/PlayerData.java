package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class PlayerData {


    private Config config;

    private Player player;


    public PlayerData(Player player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void setName(String name) {
        config.getConfig().set("island", name);
        config.saveConfig();
    }

    public String getIsland() {
        return config.getConfig().getString("island");
    }
}
