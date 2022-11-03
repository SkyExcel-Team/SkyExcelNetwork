package net.skyexcel.server.playtime.data;

import net.skyexcel.server.playtime.SkyExcelNetworkPlayTimeMain;
import org.bukkit.entity.Player;
import skyexcel.data.Time;
import skyexcel.data.file.Config;

public class PlayTime {

    private Player player;

    private Time time;

    private Config config;

    private Long playtime;


    public PlayTime(Player player) {
        this.player = player;

        this.config = new Config("data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkPlayTimeMain.plugin);

        this.time = new Time();


        this.playtime = time.SECOND_IN_MILLIS();
    }

    public String translate() {
        String result = "";
        if (this.time.DAY() != -1) {
            result = this.time.DAY() + " 일 " + this.time.HOUR() + " 시간 " + this.time.MINUTE() + " 분 " + this.time.SECOND() + " 초";
        } else if (this.time.HOUR() != -1) {
            result = this.time.HOUR() + " 시간 " + this.time.MINUTE() + " 분 " + this.time.SECOND() + " 초";
        } else if (this.time.MINUTE() != -1) {
            result = this.time.MINUTE() + " 분 " + this.time.SECOND() + " 초";
        } else if (this.time.SECOND() != -1) {
            result = this.time.SECOND() + " 초";
        }
        return result;
    }

    public void saveDefault() {
        if (config.getConfig().get("playtime") == null) {
            config.setLong("playtime", 0);
        }
    }

    public long getPlayTime() {
        return config.getLong("playtime");
    }

    public Player getPlayer() {
        return player;
    }
}