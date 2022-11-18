package net.skyexcel.server.job.data.fisher;


import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.stat.Statable;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

//TODO 물고기 만들고, 난 후 할꺼임.
public class Anglers extends Statable implements JobPlayerData {

    private Player player;
    private String path = "job/";

    public Anglers(Player player) {
        super("Anglers", "job/" + player.getUniqueId() + "/Anglers", player);
        this.player = player;
    }

    public void setDefault() {
        path = path + player.getUniqueId();
        Config config = new Config(path + "/" + getName());

        config.setPlugin(SkyExcelNetworkJobMain.plugin);
        config.getConfig().set("level", 0);
        config.saveConfig();
    }

}
