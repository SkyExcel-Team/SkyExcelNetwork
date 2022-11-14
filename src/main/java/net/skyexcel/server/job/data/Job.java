package net.skyexcel.server.job.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
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

    public void selectJob(Player player, JobType type) {
        this.jobType = type;
        this.config.setString("Job", type.name());
        switch (type) {
            case FISH -> {
                player.sendMessage("낚시꾼 직업을 선택 하였습니다.");
            }
            case FARM -> {
                player.sendMessage("농부 직업을 선택 하였습니다.");
            }
            case MINE -> {
                player.sendMessage("광부 직업을 선택 하였습니다.");
            }
            case NULL -> {

            }
        }
    }

    public JobType getJobType() {
        return JobType.valueOf(config.getString("Job"));
    }


}
