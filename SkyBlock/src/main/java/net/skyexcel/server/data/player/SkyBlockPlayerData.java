package net.skyexcel.server.data.player;

import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.island.SkyBlock;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SkyBlockPlayerData {


    private Config config;

    private Player player;


    public SkyBlockPlayerData(Player player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyBlockCore.plugin);
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

        return (config.getConfig().get("island.name") != null);

    }

    public String getIsland() {
        return config.getConfig().getString("island.name");
    }
}
