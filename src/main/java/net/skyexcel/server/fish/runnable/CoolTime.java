package net.skyexcel.server.fish.runnable;

import org.bukkit.scheduler.BukkitRunnable;
import skyexcel.data.Time;

public class CoolTime extends BukkitRunnable {

    private Time time;

    public CoolTime(){
        time = new Time(0,15,0,0);
    }

    @Override
    public void run() {
        time.minSecond(1);

    }
}
