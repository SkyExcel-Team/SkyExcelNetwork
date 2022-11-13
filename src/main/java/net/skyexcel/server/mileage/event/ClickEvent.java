package net.skyexcel.server.mileage.event;

import net.skyexcel.server.mileage.data.Data;
import net.skyexcel.server.mileage.data.shop.MileageShop;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.util.Stockable;

public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        HumanEntity var3 = event.getWhoClicked();
        if (var3 instanceof Player player) {
            Inventory inv = event.getClickedInventory();
            if (inv != null) {
                ItemStack itemStack = event.getCurrentItem();

                Data data = new Data();

                MileageShop shop = data.get(player);
                if (shop != null) {
                    if (event.isShiftClick()) {
//                        shop.setItemStack(itemStack);
//                        shop.setBuy(10000);
//                        shop.setType(Stockable.SetType.BUY);
                        player.closeInventory();
                        event.setCancelled(true);
                    }
                }
            }
        }

    }

}
