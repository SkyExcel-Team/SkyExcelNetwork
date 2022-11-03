package net.skyexcel.server.cashshop.data;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import skyexcel.data.file.Config;

public class CashShop {

    private String name;

    private Config config;


    public CashShop(String name) {
        this.name = name;
        this.config = new Config("CashShop-cash/" + name);
        this.config.setPlugin(SkyExcelNetworkCashShopMain.plugin);
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, "test");

        player.openInventory(inv);
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
