package net.skyexcel.server.skyblock.data.island;

import net.skyexcel.server.skyblock.data.SkyBlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeleteRunnable extends BukkitRunnable {

    private int i = 15;

    private Player player;

    public DeleteRunnable(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (i == 0) {
            SkyBlockData.delete.remove(player.getUniqueId());
            cancel();
        } else {
            i--;
        }
    }
}
