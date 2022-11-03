package net.skyexcel.server.skyblock.data.island.vault;

import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.util.Translate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class SkyBlockVaultRecord {

    private Type type;
    private String name;

    private Config record;

    public SkyBlockVaultRecord(String name) {
        this.name = name;
        this.record = new Config("SkyBlock/" + name + "/record/VaultRecord");
        this.record.setPlugin(SkyExcelNetworkSkyBlockMain.plugin);

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
