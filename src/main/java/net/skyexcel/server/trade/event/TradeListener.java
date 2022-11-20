package net.skyexcel.server.trade.event;

import net.skyexcel.server.trade.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Set;


public class TradeListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void test(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        int slot = event.getSlot();


    }


    @EventHandler
    public void onDrag(InventoryDragEvent event) {

        if (event.getWhoClicked() instanceof Player player) {
            @NotNull Set<Integer> slots = event.getRawSlots();



        }
    }
}
