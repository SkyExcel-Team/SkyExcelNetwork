package net.skyexcel.server.mileage.data;

import net.skyexcel.server.skyblock.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class Exchange {
    private Player player;

    private Inventory inv;

    public Exchange(Player player) {
        this.player = player;
    }

    public void open() {
        inv = Bukkit.createInventory(null, 27, "마일리지 교환소");
        Items.newItem("", Material.CHAIN, 1, List.of(), 13, inv);
        player.openInventory(inv);
    }
}
