package net.skyexcel.server.job.data.type;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobMeta;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.Stat;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class MineWorker extends JobMeta implements JobPlayerData {

    private Stat stat;

    public MineWorker() {
        super("광부");

    }
    public void setDefault(Player player) {
        setLevel(player, 0);
        setStatPoint(player, "statPoint", 0);
    }
}
