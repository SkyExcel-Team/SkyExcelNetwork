package net.skyexcel.server.dailycheck.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.playtime.data.PlayTime;
import org.bukkit.OfflinePlayer;
import skyexcel.data.Time;
import skyexcel.data.file.Config;

public class DailyCheck {

    private OfflinePlayer offlinePlayer;

    private Config config;

    private PlayTime playTime;

    private Time time;


    public DailyCheck(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.config = new Config("DailyCheck/" + offlinePlayer.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        this.playTime = new PlayTime(offlinePlayer);
        this.time  = new Time(playTime.getPlayTime());
    }

    public void gui(){

    }

}
