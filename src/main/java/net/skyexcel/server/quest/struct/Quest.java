package net.skyexcel.server.quest.struct;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.quest.gui.button.Button;
import net.skyexcel.server.trade.data.Data;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.Date;

public class Quest {
    private String name;

    private OfflinePlayer player;

    private Config config;

    private int max;

    private Button button;

    public Quest(String name, OfflinePlayer player, Button button) {
        this.name = name;
        this.player = player;
        config = new Config("quest/" + player.getUniqueId());
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        this.button = button;
    }


    public void setMax(int max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    /***
     * 플레이어가 퀘스트에 맞는 액션을 취할시 값을 더해준다.
     * 해당 퀘스트 경로가 숫자일 때만 작동하며, 아예 없을 경우에도 작동하지 않는다.
     * @param amount 더할 값
     */
    public void add(int amount) {
        String path = "quest." + name;

        Date date = new Date(System.currentTimeMillis());
        System.out.println(date.getHours());

        if (config.getConfig().get(path) != null) {
            if (config.getConfig().get(path) instanceof Integer) {
                int value = config.getInteger(path);
                config.setInteger(path, value + amount);
            }
        }
    }

    /**
     * 플레이어가 퀘스트를 완료하여 해당 퀘스트 변수를 true로 만들어주는 메소드
     */
    public void complete() {
        String path = "quest." + name;
        config.setBoolean(path, true);
    }

    public void set() {
        String path = "quest." + name;
        config.setBoolean(path, true);
    }

    /***
     *플레이어가 퀘스트에 대한 액션을 취할 시, 해당 액션이 조건과 맞는지 확인하는 메소드 이다.
     * 만약, 해당 퀘스트가 없다면 false 를 반환한다.
     * @return 퀘스트 경로에 값이 없다면 false를 반환한다.
     */
    public boolean isComplete() {
        String path = "quest." + name;
        if (!config.getBoolean(path)) {
            int value = config.getInteger(path);
            return value == max;
        }
        return config.getBoolean(path);
    }


    public int getNow() {
        String path = "quest." + name;
        if (config.getConfig().get(path) != null) {
            return config.getInteger(path);
        }
        return -1;
    }

    /**
     * 플레이어가 퀘스트에 대한 행동을 취할 시, 해당 액션이 퀘스트 조건과 맞는지 확인하는 메소드
     *
     * @return 만약, 플레이어가 해당 퀘스트를 가지고 있지 않으면 무조건 false를 반환한다.
     */
    public boolean isNotMax() {
        String path = "quest." + name;
        if (config.getConfig().get(path) != null) {
            int value = config.getInteger(path);

            return value <= max;
        }
        return false;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public Button getButton() {
        return button;
    }
}
