package net.skyexcel.server.job.data.type;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.stat.Scarecrow;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Farmer extends Job {


    private Scarecrow scarecrow;


    public Farmer(OfflinePlayer player) {
        super(player);
    }

    public void run(Player player) {
        scarecrow = new Scarecrow(player.getLocation(), 1);
        scarecrow.spawn(player);
    }
}
