package net.skyexcel.server.data;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

import java.util.Arrays;
import java.util.List;

public class GiftBox {

    private Player player;
    private Config config;

    private GUI gui;

    private int MAX_PAGE = 5;

    private Inventory inv;

    final int[] black_glass = {0, 1, 2, 3, 5, 6, 7, 8, 17, 9, 18, 26, 27, 35, 36, 44, 45, 46, 47, 51, 52, 53};


    public GiftBox(Player player) {
        this.player = player;
        this.config = new Config("data/" + player.getUniqueId());
        this.config.setPlugin(SkyExcelNetwork.plugin);
        gui = new GUI(config);
    }

    public void open() {

    }

    public void next() {
        Inventory inv = Bukkit.createInventory(null, 54, "선물함");


        inv.setItem(4, Items.playerSkull(player.getName(), "", Arrays.asList("")));
        Arrays.stream(black_glass).forEach(slots -> {
            Items.newItem("", Material.BLACK_STAINED_GLASS_PANE, 1, List.of(""), slots, inv);
        });
        this.inv = inv;
        player.openInventory(inv);
    }

    public void save() {
        Arrays.stream(black_glass).forEach(slots -> {
            ItemStack items = inv.getItem(slots);
            if (items != null)
                inv.removeItem(items);

        });
        gui.saveInventory("shop.1", inv);
    }


    public Inventory getInv() {
        return inv;
    }
}
