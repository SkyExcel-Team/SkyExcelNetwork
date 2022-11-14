package net.skyexcel.server.snowy.data;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class SnowToggleData {
    private Config config;
    private OfflinePlayer player;

    public SnowToggleData(OfflinePlayer player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
    }

    public OfflinePlayer getPlayer()  {
        return this.player;
    }

    public void setPlayer(OfflinePlayer player) {
        this.player = player;
    }

    public Config getConfig() {
        return this.config;
    }

    public void setVisibility(Boolean visibility) {
        this.config.setBoolean("snow", visibility);
    }

    public Boolean getVisibility() {
        return this.config.getBoolean("snow");
    }
}
