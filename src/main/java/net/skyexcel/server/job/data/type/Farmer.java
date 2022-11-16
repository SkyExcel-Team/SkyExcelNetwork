package net.skyexcel.server.job.data.type;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobMeta;
import net.skyexcel.server.job.data.stat.Scarecrow;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Farmer extends JobMeta {


    private Scarecrow scarecrow;


    public Farmer() {
        super("농부");
    }

    public void run(Player player) {
        scarecrow = new Scarecrow(player.getLocation(), 1);
        scarecrow.spawn(player);
    }
}
