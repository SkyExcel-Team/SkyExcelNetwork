package net.skyexcel.server.job.data;

import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Skeleton;
import skyexcel.data.file.Config;

import java.util.List;

public class Job {


    private Stat stat;

    private JobMeta jobMeta;

    private JobType jobType;

    private Config config;

    public Job(OfflinePlayer player){
        this.config = new Config("data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkJobMain.plugin);
        this.jobType = JobType.valueOf(config.getString("job"));
        this.jobMeta = new JobMeta(jobType.getName());

        switch (jobType){
            case FARM -> {
                stat = new Stat("");
                jobMeta.setDescription(List.of("이건 농부에 대한 설명 입니다."));
            }
            case FISHERMAN -> {
                jobMeta.setDescription(List.of("이건 낚시꾼 대한 설명 입니다."));
            }
            case MINEWORKER -> {
                jobMeta.setDescription(List.of("이건 광부에 대한 설명 입니다."));
            }
        }
    }




    public JobMeta getJobMeta() {
        return jobMeta;
    }
}
