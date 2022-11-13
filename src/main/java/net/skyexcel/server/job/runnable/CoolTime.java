package net.skyexcel.server.job.runnable;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import skyexcel.data.Time;

public class CoolTime extends BukkitRunnable {
    private Time time;

    private ArmorStand armorStand;

    private Player player;

    public CoolTime(ArmorStand armorStand, Player player) {
        this.player = player;
        time = new Time(0, 0, 0, 15);
        this.armorStand = armorStand;
    }

    @Override
    public void run() {
        if (time.SECOND() == 0) {

            player.sendMessage("허수아비가 없어졌습니다!");
            armorStand.remove();
            cancel();

        }
        time.minSecond(1);
        armorStand.setCustomName(time.SECOND() + " 초 남았습니다");
    }
}