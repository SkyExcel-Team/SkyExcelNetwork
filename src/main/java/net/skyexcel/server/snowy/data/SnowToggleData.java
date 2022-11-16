package net.skyexcel.server.snowy.data;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class SnowToggleData {
    private Config config;

    public SnowToggleData(OfflinePlayer player) {
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);
        config.loadDefualtConfig();
        config.saveDefualtConfig();

        if (!config.getConfig().contains("snow"))
            config.setBoolean("snow", true);
    }

    public Config getConfig() {
        return this.config;
    }
}
