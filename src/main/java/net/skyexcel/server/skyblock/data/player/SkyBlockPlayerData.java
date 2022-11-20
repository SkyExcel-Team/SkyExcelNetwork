package net.skyexcel.server.skyblock.data.player;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class SkyBlockPlayerData {
    private Config config;

    private OfflinePlayer player;

    public SkyBlockPlayerData(OfflinePlayer player) {
        this.player = player;
        config = new Config("data/SkyBlock/Player/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
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
            config.setLocation("island.spawn", player.getPlayer().getLocation());
            config.saveConfig();
            return true;
        }
        return false;
    }

    /**
     * @param location 섬을 지우면 이 값에 좌표가 저장이 됨.
     *                 다시 생성할 때 이 좌표를 사용하면 끝
     */
    public void setOriginLocation(Location location) {
        config.setLocation("island.loc", location);
    }

    public Location getOriginLocation() {
        return config.getLocation("island.loc");
    }


    public boolean hasIsland() {
        if (config != null) {

            return (config.getConfig().getString("island.name") != null && getOriginLocation() == null);
        }

        return false;
    }

    public Config getConfig() {
        return config;
    }

    public String getIsland() {
        return config.getConfig().getString("island.name");
    }
}
