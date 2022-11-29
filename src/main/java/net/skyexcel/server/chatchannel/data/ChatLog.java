package net.skyexcel.server.chatchannel.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.OfflinePlayer;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class ChatLog {

    private Config config;

    private OfflinePlayer offlinePlayer;

    private String log;

    private List<String> logs = new ArrayList<>();


    public ChatLog(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        config = new Config("chat/log/" + offlinePlayer.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    public ChatLog(OfflinePlayer offlinePlayer, String log) {
        this.offlinePlayer = offlinePlayer;
        config = new Config("chat/log" + offlinePlayer.getUniqueId());
        this.log = log;
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
    }

    /**
     * 플레이어가 서버에 입장했을때, 콘피그에서 불러온다 값이 없으면 빈 리스트를 생성한다.
     */
    public void load() {
        if (config.getConfig().get("logs") == null) {
            logs = new ArrayList<>();
            config.getConfig().set("logs", logs);
            config.saveConfig();
        } else {
            logs = config.getConfig().getStringList("log");
        }
    }


    /**
     * 플레이어가 서버를 나갈때, 채팅 로그를 저장합니다.
     */
    public void save() {
        config.getConfig().set("log", logs);
        config.saveConfig();
    }


    public void addLog(String msg) {
        System.out.println(logs.add(msg));
    }
}
