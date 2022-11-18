package net.skyexcel.server.job.data.farmer;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.stat.Statable;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class Blessing extends Statable implements JobPlayerData {
    private Player player;
    private String path = "job/";

    public Blessing(Player player) {
        super("농부의 축복", "job/" + player.getUniqueId() + "/blessing", player);
        this.player = player;
    }


    public void setDefault() {
        path = path + player.getUniqueId();
        Config config = new Config(getPath());

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }

}
