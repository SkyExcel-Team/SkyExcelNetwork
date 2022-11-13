package net.skyexcel.server.job.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class Job {

    private OfflinePlayer player;

    private Config config;

    private JobType jobType = JobType.NULL;


    public Job(OfflinePlayer player) {
        this.player = player;
        config = new Config("data/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public void selectJob(JobType type) {
        this.jobType = type;
        this.config.setString("Job", type.name());
    }

    public JobType getJobType() {
        return JobType.valueOf(config.getString("Job"));
    }



}
