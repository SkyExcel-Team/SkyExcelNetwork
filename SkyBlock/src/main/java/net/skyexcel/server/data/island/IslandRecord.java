package net.skyexcel.server.data.island;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.util.Translate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class IslandRecord {
    private Player player;
    private int amount;
    private Type type;
    private String name;

    private Config record;

    public IslandRecord(String name) {
        this.name = name;
        this.record = new Config("island/" + name + "/record/IslandRecord");
        this.record.setPlugin(SkyExcelNetwork.plugin);

    }

    public void record(Player player, Player target, String reason, Type type) {

        try {
            ConfigurationSection section = record.getConfig().createSection("record." + record.getConfig().getConfigurationSection("record").getKeys(false).size());
            section.set("time", Translate.getDate());
            section.set("player", player.getName());
            section.set("target", target.getName());
            assert reason != null;
            section.set("reason", reason);
            section.set("action", type.getName());
            record.saveConfig();

        } catch (NullPointerException e) {

            ConfigurationSection newSection = record.getConfig().createSection("record.0");
            newSection.set("time", Translate.getDate());
            newSection.set("player", player.getName());
            newSection.set("target", target.getName());
            assert reason != null;
            newSection.set("reason", reason);
            newSection.set("action", type.getName());
            record.saveConfig();
        }
    }


    public enum Type {
        KICK("추방"), WITHDRAW("출금"), JOIN("입장");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
