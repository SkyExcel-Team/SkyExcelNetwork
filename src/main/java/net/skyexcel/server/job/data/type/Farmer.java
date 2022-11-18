package net.skyexcel.server.job.data.type;

import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobMeta;
import net.skyexcel.server.job.data.JobPlayerData;
import net.skyexcel.server.job.data.farmer.Scarecrow;

import org.bukkit.entity.Player;

public class Farmer extends JobMeta implements JobPlayerData {


    private Scarecrow scarecrow;


    public Farmer() {
        super("농부");
    }

    public void run(Player player) {
        if (!JobData.scarecrow.containsKey(player.getUniqueId())) {
            scarecrow = new Scarecrow(player.getLocation(), 1, player);
            scarecrow.spawn(player);
            JobData.scarecrow.put(player.getUniqueId(), scarecrow);
        }

    }

    public void setDefault(Player player) {
        setLevel(player, 0);
        setStatPoint(player, "statPoint", 0);
    }
}
