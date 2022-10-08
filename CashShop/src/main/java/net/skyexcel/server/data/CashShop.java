package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.entity.Player;
import skyexcel.data.file.Config;

public class CashShop {

    private String name;

    private Config config;


    public CashShop(String name) {
        this.name = name;
        this.config = new Config("cash/" + name);
        this.config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void create(Player player) {

        
        config.setString("name", name);
        config.saveConfig();
    }

    public void delete(Player player) {
        config.renameFile("trash/" + name);
        config.saveConfig();
        config.delete();
    }
}
