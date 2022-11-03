package net.skyexcel.server.mileage.data.shop;

import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.mileage.data.StringData;
import net.skyexcel.server.mileage.InventoryUpdate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class EditGUI {

    public void open(Player player) {

        InventoryUpdate.updateInventory(SkyExcelNetworkMileageMain.plugin, player, StringData.gui_title());

        Inventory inv = Bukkit.createInventory(null, 27, StringData.gui_title());


        Items.newItem("", Material.GRASS_BLOCK, 1, StringData.buy_sell_lore(), StringData.buy_sell_slot(), inv);

        player.openInventory(inv);
    }
}
