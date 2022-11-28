package net.skyexcel.server.flyticket.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.flyticket.SkyExcelNetworkFlyTicketMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.Time;
import skyexcel.data.file.Config;

public class FlyTime {

    private Time time;

    private Config config;
    private OfflinePlayer offlinePlayer;


    /***
     * 서버에 있는 오프라인 유저를 통해 Time 값을 가져 오게 하는 생성자 입니다.
     * 이 생성자를 통해 플레이어가 얼마 만큼의 플라이 티켓 시간을 가지고 있는지 확인이 가능합니다.
     * @param offlinePlayer 플라이 티켓 시간을 불러올 오프라인 플레이어.
     */
    public FlyTime(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.config = new Config("fly/" + offlinePlayer.getUniqueId());
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        
    }

    public void setDefault() {
        if (this.config == null)
            this.config.setLong("left", 0);
    }


    public void addTimeAsYear(int Year, int Month, int Day, int Hour, int Minutes, int Second) {
        Time newTime = new Time(Year, Month, Day, Hour, Minutes, Second);
    }


    public Time getTime() {
        return time;
    }
}
