package net.skyexcel.server.data.shop;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.StringData;
import net.skyexcel.server.InventoryUpdate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class EditGUI {

    public void open(Player player) {

        InventoryUpdate.updateInventory(SkyExcelNetwork.plugin, player, StringData.gui_title());

        Inventory inv = Bukkit.createInventory(null, 27, StringData.gui_title());


        Items.newItem("", Material.GRASS_BLOCK, 1, StringData.buy_sell_lore(), StringData.buy_sell_slot(), inv);

        player.openInventory(inv);
    }
}
