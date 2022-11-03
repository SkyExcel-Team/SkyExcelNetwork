package net.skyexcel.server.skyblock.data.player;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class SkyBlockPlayerData {
    private Config config;

    private OfflinePlayer player;

    public SkyBlockPlayerData(OfflinePlayer player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);

    }

    public void setName(String name) {
        config.getConfig().set("island.name", name);
        config.saveConfig();
    }

    public boolean isOwner() {
        if (config != null) {
            if (hasIsland()) {
                SkyBlock skyBlock = new SkyBlock(getIsland());
                return this.player.getUniqueId().toString().equalsIgnoreCase(skyBlock.getOwner());
            }
        }
        return false;
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
        return (config.getConfig().getString("island.name").equalsIgnoreCase(""));
    }

    public Config getConfig() {
        return config;
    }

    public String getIsland() {
        return config.getConfig().getString("island.name");
    }
}
