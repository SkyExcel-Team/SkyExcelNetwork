package net.skyexcel.server.essentials.playtime.scheduler;

import net.skyexcel.server.essentials.playtime.data.PlayTime;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayTimeScheduler extends BukkitRunnable {

    private Player player;
    private PlayTime playTime;

    private long period;

    public PlayTimeScheduler(Player player, long period) {
        this.player = player;
        this.playTime = new PlayTime(player);
        this.period = period;
    }


    @Override
    public void run() {
        playTime.addSecond(getPeriod() / 20);//딜레이 시간 만큼 플레이어 타임 추가.
        player.sendMessage("플레이 타임 추가 " + playTime.getTime().SECOND());
    }


    public long getPeriod() {
        return period;
    }
}
