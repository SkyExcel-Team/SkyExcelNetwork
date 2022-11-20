package net.skyexcel.server.trade.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TradeGUI {

    private Player player;

    private Inventory inv;

    public TradeGUI(Player player) {
        this.player = player;
    }

    public void open() {
        this.inv = Bukkit.createInventory(null, 54, "거래");
        player.openInventory(inv);
    }
}
