package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class ChatRecord {

    private String name;

    private Config record;

    private List<String> message;


    public ChatRecord(String name) {
        this.name = name;
        this.record = new Config("data/" + name);
        this.record.setPlugin(SkyExcelNetwork.plugin);

        if (record.getConfig().get("record.message") == null) {
            record.getConfig().set("record.message", new ArrayList<>());
            record.saveConfig();
        }

        if (record.getConfig().get("record.time") == null) {
            record.getConfig().set("record.time", new ArrayList<>());
            record.saveConfig();
        } else {
            message = record.getConfig().getStringList("record.time");
        }
    }

    /**
     * 같은 채팅을 여러번 반복하여 쳤을 경우 repeated 횟수가 증가한다.
     *
     * @param player
     * @param msg
     */
    public void record(Player player, String msg) {

        record.setString("record.player", player.getDisplayName());

        if (!message.contains(msg)) {
            message.add(msg);
        } else {
            record.getConfig().set("record.message." + msg, 1);
            record.saveConfig();
        }

        record.saveConfig();
    }
}