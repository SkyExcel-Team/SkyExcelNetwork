package net.skyexcel.server.job.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.Objects;


public class Job {


    private JobType jobType = JobType.NONE;

    private Config config;

    private OfflinePlayer offlinePlayer;

    public Job(OfflinePlayer player) {

        this.config = new Config("data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        this.offlinePlayer = player;
    }


    public void setJobType(JobType type) {
        this.jobType = type;
        this.config.setString("job", jobType.name());
    }

    public void reset() {
        this.config.removeKey("job");
        this.config.deleteDir("job/" + offlinePlayer.getUniqueId());
    }

    public boolean hasJob() {
        return !getType().equals(JobType.NONE);
    }


    public void increase() {

    }

    public JobType getType() {

        return config.getString("job") != null ? JobType.valueOf(config.getString("job")) : JobType.NONE;
    }
}
