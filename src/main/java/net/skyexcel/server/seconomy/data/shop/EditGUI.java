package net.skyexcel.server.seconomy.data.shop;

import net.skyexcel.server.seconomy.InventoryUpdate;
import net.skyexcel.server.seconomy.SkyExcelNetwork;
import net.skyexcel.server.seconomy.data.StringData;
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
