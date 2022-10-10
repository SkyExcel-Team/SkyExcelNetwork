package net.skyexcel.server.data.vault;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.util.Translate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class VaultRecord {
    private Player player;
    private int amount;
    private Type type;
    private String name;

    private Config record;

    public VaultRecord(String name) {
        this.name = name;
        this.record = new Config("island/" + name + "/record/VaultRecord");
        this.record.setPlugin(SkyExcelNetwork.plugin);

    }

    public void record(Player player, int amount, Type type) {

        try {
            ConfigurationSection section = record.getConfig().createSection("record." + record.getConfig().getConfigurationSection("record").getKeys(false).size());
            if (section != null) {
                section.set("time", Translate.getDate());
                section.set("player", player.getName());
                section.set("amount", amount);
                section.set("purchase", type.getName());
                record.saveConfig();
            }

        } catch (NullPointerException e) {
            System.out.println("test");
            ConfigurationSection newSection = record.getConfig().createSection("record.0");
            newSection.set("time", Translate.getDate());
            newSection.set("player", player.getName());
            newSection.set("amount", amount);
            newSection.set("purchase", type.getName());
            record.saveConfig();
        }


    }

    public enum Type {
        DEPOSIT("입금"), WITHDRAW("출금");

        private String name;


        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
