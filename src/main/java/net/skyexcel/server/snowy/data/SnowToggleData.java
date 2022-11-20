package net.skyexcel.server.snowy.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class SnowToggleData {
    private Config config;

    public SnowToggleData(OfflinePlayer player) {
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        config.loadDefualtConfig();
        config.saveDefualtConfig();

        if (!config.getConfig().contains("snow"))
            config.setBoolean("snow", true);
    }

    public Config getConfig() {
        return this.config;
    }
}
