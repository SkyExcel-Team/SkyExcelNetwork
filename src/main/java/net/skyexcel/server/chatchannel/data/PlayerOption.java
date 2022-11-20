package net.skyexcel.server.chatchannel.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class PlayerOption {

    private Config config;

    private Player player;


    public PlayerOption(Player player) {
        this.player = player;
        this.config = new Config("ChatChannel-option/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }


}
