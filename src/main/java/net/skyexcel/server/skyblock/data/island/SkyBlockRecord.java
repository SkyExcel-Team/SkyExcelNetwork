package net.skyexcel.server.skyblock.data.island;

import net.skyexcel.server.skyblock.util.Translate;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SkyBlockRecord {

    private String name;

    private Config record;

    public SkyBlockRecord(String name) {
        this.name = name;
        this.record = new Config("SkyBlock/" + name + "/record/IslandRecord");
        this.record.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);

    }

    public void playerRecord(Player player, OfflinePlayer target, String reason, Type type) {
        String time = Translate.getDate();
        try {
            ConfigurationSection section = record.getConfig().createSection("record." + record.getConfig().getConfigurationSection("record").getKeys(false).size());
            section.set("time", time);
            section.set("player", player.getName());
            section.set("target", target.getName());
            assert reason != null;
            section.set("reason", reason);
            section.set("action", type.getName());
            record.saveConfig();

        } catch (NullPointerException e) {

            ConfigurationSection newSection = record.getConfig().createSection("record.0");
            newSection.set("time", time);
            newSection.set("player", player.getName());
            newSection.set("target", target.getName());
            assert reason != null;
            newSection.set("reason", reason);
            newSection.set("action", type.getName());
            record.saveConfig();
        }
    }

    public void skyblockRecord(Player player, String reason, Type type) {

        try {
            ConfigurationSection section = record.getConfig().createSection("record." + record.getConfig().getConfigurationSection("record").getKeys(false).size());
            section.set("time", Translate.getDate());
            section.set("player", player.getName());

            assert reason != null;
            section.set("reason", reason);
            section.set("action", type.getName());
            record.saveConfig();

        } catch (NullPointerException e) {

            ConfigurationSection newSection = record.getConfig().createSection("record.0");
            newSection.set("time", Translate.getDate());
            newSection.set("player", player.getName());

            assert reason != null;
            newSection.set("reason", reason);
            newSection.set("action", type.getName());
            record.saveConfig();
        }
    }


    public enum Type {
        KICK("추방"), JOIN("입장"), CREATE("생성"), BLACKLIST("블랙리스트");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
