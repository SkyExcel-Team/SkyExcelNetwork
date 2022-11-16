package net.skyexcel.server.job.data;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

import java.util.Objects;


public class Job {


    private JobType jobType = JobType.NONE;

    private Config config;

    public Job(OfflinePlayer player) {
        this.config = new Config("data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkJobMain.plugin);


//        switch (jobType) {
//            case FARM -> {
//                jobMeta.setDescription(List.of("이건 농부에 대한 설명 입니다."));
//            }
//            case FISHERMAN -> {
//                jobMeta.setDescription(List.of("이건 낚시꾼 대한 설명 입니다."));
//            }
//            case MINEWORKER -> {
//                jobMeta.setDescription(List.of("이건 광부에 대한 설명 입니다."));
//            }
//        }
    }


    public void setJobType(JobType type) {
        this.jobType = type;
        this.config.setString("job", jobType.name());
    }

    public boolean hasJob() {
        return !getType().equals(JobType.NONE);
    }



    public JobType getType() {
        return config.getString("job") != null ? JobType.valueOf(config.getString("job")) : JobType.NONE;
    }
}
