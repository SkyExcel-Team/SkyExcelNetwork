package net.skyexcel.server.fish.runnable;

import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import skyexcel.data.Time;

public class CoolTime {

    private Time time;

    private BossBar bossBar;

    private Player player;

    private String title;
    private int taskID = -1;

    public CoolTime(Player player) {
        time = new Time(0, 0, 0, 15);

        this.title = "낚시대회 " + time.MINUTE() + " 분 " + time.SECOND() + " 초 남았습니다";
        bossBar = Bukkit.createBossBar(title, BarColor.BLUE, BarStyle.SOLID);
        bossBar.setVisible(true);
        this.player = player;
        bossBar.setProgress(1);
        bossBar.addPlayer(player);
    }

    public void cast() {


        double count = -1;
        double progress = 1.0;
        final double percent = 1.0 / (30);


        final int digit = (int) Math.log10(time.SECOND_IN_MILLIS()) + 1;

        for (int i = 0; i < 35; i++) {
            time.minSecond(1);
            int result = 1 ^ digit;
            System.out.println(result + " 자리수");

            System.out.println(progress + "%");

            bossBar.setProgress(progress);
            bossBar.setTitle("낚시대회 " + time.MINUTE() + " 분 " + time.SECOND() + " 초 남았습니다");

            progress = progress - percent;

            if (progress == 0) {
                bossBar.removePlayer(player);
            }
        }


//        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyExcelNetworkFishMain.plugin, new Runnable() {
//

//            final int digit = (int) Math.log10(time.SECOND_IN_MILLIS()) + 1;
//
//
//            @Override
//            public void run() {
//                time.minSecond(1);
//                int result = 1 ^ digit;
//                System.out.println(result + " 자리수");
//                System.out.println(percent + "%");
//                bossBar.setProgress(progress);
//                bossBar.setTitle("낚시대회 " + time.MINUTE() + " 분 " + time.SECOND() + " 초 남았습니다");
//                progress = progress - percent;
//                if (progress <= 0) {
//                    count++;
//                    progress = 1.0;
//                }
//
//            }
//        }, 0, 20);
    }
}
