package net.skyexcel.server.skyblock.ui.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UpgradeGUI {

    private String name;

    public UpgradeGUI(String name) {
        this.name = name;
    }


    public void create(Player player){
        Inventory inv= Bukkit.createInventory(null,45, name + " 섬 레벨");
    }
}
