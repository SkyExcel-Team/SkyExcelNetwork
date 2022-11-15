package net.skyexcel.server.job.data.type;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.Stat;
import org.bukkit.OfflinePlayer;

public class MineWorker extends Job {

    private Stat stat;

    public MineWorker(OfflinePlayer player) {
        super(player);

    }
}
