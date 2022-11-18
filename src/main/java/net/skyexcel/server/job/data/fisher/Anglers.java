package net.skyexcel.server.job.data.fisher;


import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.StatMeta;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.List;

//TODO 물고기 만들고, 난 후 할꺼임.
public class Anglers extends StatMeta implements JobPlayerData {

    private Player player;
    private String path = "job/";

    public Anglers(Player player) {
        super("강태공", List.of("", "§6§l│ §f한단계 높은 §e등급§f의 §9물고기§f(인챈트)(을)를 얻습니다. ", "",
                ChatColor.GRAY + "1/5"));
        this.player = player;
    }

    public void setDefault() {
        path = path + player.getUniqueId();
        Config config = new Config("job/" + player.getUniqueId() + "/Anglers");

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }
}
