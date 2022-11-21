package net.skyexcel.server.cashprofile.gui;

import net.skyexcel.server.cashprofile.SkyExcelNetworkCashProfileMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import skyexcel.data.file.Config;

public class CashGUI {
    private Config config;

    private Player player;

    public CashGUI(Player player) {
        this.player = player;
    }

    public CashGUI() {
        this.config = new Config("CashProfileConfig");
        this.config.setPlugin(SkyExcelNetworkCashProfileMain.getPlugin());
        config.loadDefaultPluginConfig();
    }

    public void open() {
        Inventory inv = Bukkit.createInventory(null, gui_size(), "");

        player.openInventory(inv);
    }


    private int gui_size() {
        return config.getInteger("playerprofile_gui_settings");
    }

}
