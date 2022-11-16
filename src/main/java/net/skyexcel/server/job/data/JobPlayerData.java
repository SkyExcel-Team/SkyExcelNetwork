package net.skyexcel.server.job.data;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public interface JobPlayerData {

    default double getStatPoint(Player player) {
        Config config = new Config("job/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkJobMain.plugin);

        return config.getDouble("StatPoint");
    }

    default void setStatPoint() {

    }
}
