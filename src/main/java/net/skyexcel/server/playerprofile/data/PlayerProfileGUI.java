package net.skyexcel.server.playerprofile.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerProfileGUI {

    private Player player;


    public PlayerProfileGUI(Player player) {
        this.player = player;
    }

    public void onGUI() {
        Inventory inv = Bukkit.createInventory(null, 27, "");
    }
}
