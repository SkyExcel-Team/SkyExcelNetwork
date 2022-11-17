package net.skyexcel.server.job.data.type;

import net.skyexcel.server.job.data.JobMeta;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.stat.WaterBucket;
import org.bukkit.entity.Player;

public class Fisher extends JobMeta implements JobPlayerData {


    private WaterBucket waterBucket;


    public Fisher() {
        super("낚시꾼");
    }

    public void setDefault(Player player) {
        setLevel(player, 0);
        setStatPoint(player, "statPoint", 0);
    }

    public WaterBucket getWaterBucket() {
        return waterBucket;
    }
}
