package net.skyexcel.server.job.data.farmer.scheduler;


import net.skyexcel.server.job.data.JobData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ArmorStand;

import org.bukkit.scheduler.BukkitRunnable;
import skyexcel.data.Time;

public class coolTime extends BukkitRunnable {

    private static Time time;

    private ArmorStand armorStand;

    private OfflinePlayer player;

    public coolTime(OfflinePlayer player) {
        time = new Time(0, 0, 0, 3);
        this.player = player;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    @Override
    public void run() {
        if (time.MINUTE() == 0 && time.SECOND() == 0) {

            armorStand.getWorld().getChunkAt((int) (armorStand.getLocation().getX() / 16), (int) (armorStand.getLocation().getZ() / 16));
            armorStand.remove();

            cancel();
            JobData.scarecrow.remove(player.getUniqueId());
        } else {
            armorStand.setCustomName(time.MINUTE() + " 분 " + time.SECOND() + " 초 남았습니다.");
            time.minSecond(1);
        }
    }
}