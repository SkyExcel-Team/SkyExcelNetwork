package net.skyexcel.server.job.data.obj;

import net.skyexcel.server.job.data.JobType;
import org.bukkit.entity.Player;

public class Selector {
    private JobType jobType;

    public Selector(JobType jobType) {
        this.jobType = jobType;
    }


    public void selectJob(Player player) {

        switch (jobType) {
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

}
