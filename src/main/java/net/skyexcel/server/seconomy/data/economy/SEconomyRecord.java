package net.skyexcel.server.seconomy.data.economy;

import net.skyexcel.server.seconomy.SkyExcelNetwork;
import net.skyexcel.server.seconomy.util.Translate;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SEconomyRecord {


    private Config record;

    private long after;

    private long before;


    public SEconomyRecord() {
        this.record = new Config("data/log/log");
        this.record.setPlugin(SkyExcelNetwork.plugin);
    }


    public void setAfter(long after) {
        this.after = after;
    }

    public void setBefore(long before) {
        this.before = before;
    }

    public void playerRecord(Player player, Player target, long amount, Type type) {
        String time = Translate.getDate();
        try {
            ConfigurationSection section = record.getConfig().createSection("record." + record.getConfig().getConfigurationSection("record").getKeys(false).size());
            section.set("time", time);
            section.set("player", player.getName());
            section.set("sendType", type.getName());
            section.set("target", target.getName());
            section.set("after", after);
            section.set("before",before);
            section.set("amount", amount);

            record.saveConfig();

        } catch (NullPointerException e) {

            ConfigurationSection newSection = record.getConfig().createSection("record.0");
            newSection.set("time", time);
            newSection.set("player", player.getName());
            newSection.set("sendType", type.getName());
            newSection.set("target", target.getName());
            newSection.set("after", after);
            newSection.set("before", before);
            newSection.set("amount", amount);


            record.saveConfig();
        }
    }

    public void playerRecord(Player player, OfflinePlayer target, long amount, Type type) {
        String time = Translate.getDate();
        try {
            ConfigurationSection section = record.getConfig().createSection("record." + record.getConfig().getConfigurationSection("record").getKeys(false).size());
            section.set("time", time);
            section.set("player", player.getName());
            section.set("sendType", type.getName());
            section.set("target", target.getName());
            section.set("after", after);
            section.set("before",before);
            section.set("amount", amount);

            record.saveConfig();

        } catch (NullPointerException e) {

            ConfigurationSection newSection = record.getConfig().createSection("record.0");
            newSection.set("time", time);
            newSection.set("player", player.getName());
            newSection.set("sendType", type.getName());
            newSection.set("target", target.getName());
            newSection.set("after", after);
            newSection.set("before", before);
            newSection.set("amount", amount);


            record.saveConfig();
        }
    }

    public enum Type {
        ADD("입금"), RESET("초기화"), REMOVE("출금"), SET("설정");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
