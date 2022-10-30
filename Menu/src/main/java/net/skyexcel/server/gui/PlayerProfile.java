package net.skyexcel.server.gui;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.GUI;


public class PlayerProfile implements Listener {

    private String title;
    private GUI gui;
    private Player player;

    public PlayerProfile(Player player) {

        this.player = player;
    }


    public PlayerProfile() {

    }

    public void open() {

    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        this.gui = null;
    }

}
