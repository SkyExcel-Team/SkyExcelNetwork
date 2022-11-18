package net.skyexcel.server.job.data.farmer;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.StatMeta;
import net.skyexcel.server.job.data.stat.Statable;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.List;

public class Blessing extends StatMeta implements JobPlayerData {
    private Player player;
    private String path = "job/";

    public Blessing(Player player) {
        super("§6농부의 축복", List.of("", "§6§l│ §f농작물을 캘시, §6일정확률§f로 §6농작물§f을 §a추가 §f드롭됩니다. ",
                "§6§l│ §f적용되는 농작물:",
                "§6§l│ §f밀",
                "§6§l│ §f 감자",
                "§6§l│ §f 당근",
                "§6§l│ §f ", ""));
        this.player = player;
    }


    public void setDefault() {
        path = path + player.getUniqueId();
        Config config = new Config("job/" + player.getUniqueId() + "/blessing");

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }

}
