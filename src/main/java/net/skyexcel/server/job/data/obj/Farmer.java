package net.skyexcel.server.job.data.obj;

import net.skyexcel.server.job.data.Characteristic;
import net.skyexcel.server.job.data.JobType;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

public class Farmer extends Characteristic {


    private JobType jobType = JobType.FARM;

    private Config config;

    private Stat a;


    private Stat b;

    public Farmer(OfflinePlayer player) {
        super("농부");
        this.config = new Config("data/" + player.getUniqueId());
        this.a = new Stat("농부의 축복", config);
        this.b = new Stat("허수아비", config);
    }


}
