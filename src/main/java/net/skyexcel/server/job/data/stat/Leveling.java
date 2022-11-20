package net.skyexcel.server.job.data.stat;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public interface Leveling {
    public void levelUp();

    public default double getLevel(Player player, String name) {
        Config config = new Config("job/" + player.getUniqueId() + "/" + name);
        config.setPlugin(SkyExcelNetworkMain.getPlugin());

        return config.getLong("level");
    }
}
