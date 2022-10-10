package net.skyexcel.server.gui;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import skyexcel.gui.GUI;

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
        if (gui == null) {
            gui = new GUI(SkyExcelNetwork.plugin);
            gui.createGUI("PlayerProfile", 45);

            gui.line(new ItemStack(Material.STONE, 1), 0, action -> {
                player.sendMessage("빼애애애애애애액");
            });

            gui.line(new ItemStack(Material.GRASS, 1), 1, action -> {
                action.getPlayer().sendMessage("test");
            });

            gui.open(player);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        this.gui = null;
    }

}
