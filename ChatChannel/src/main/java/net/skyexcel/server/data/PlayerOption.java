package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class PlayerOption {

    private Config config;

    private Player player;


    public PlayerOption(Player player) {
        this.player = player;
        this.config = new Config("option/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetwork.plugin);
    }


}
