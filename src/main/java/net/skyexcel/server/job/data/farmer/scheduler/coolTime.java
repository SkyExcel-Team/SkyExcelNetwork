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
            System.out.println(player.getName() + " 님의 허수아비 사라짐.");

            //TODO 플레이어가 아에 없을땐 이 메소드가 작동이 안됨..... 이건 뭐 버킷 api 문제라
            // Location 변수를 콘피그에 넣어서 거기에 Armorstand가 있으면 remove 해야될듯? (서버가 켜질때)
            // 해당 아머스텐드 청크
            armorStand.remove();
            cancel();
            JobData.scarecrow.remove(player.getUniqueId());
        } else {
            armorStand.setCustomName(time.MINUTE() + " 분 " + time.SECOND() + " 초 남았습니다.");
            time.minSecond(1);
        }
    }
}