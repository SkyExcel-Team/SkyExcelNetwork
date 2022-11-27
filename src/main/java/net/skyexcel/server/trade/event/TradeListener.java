package net.skyexcel.server.trade.event;

import net.skyexcel.server.trade.data.Data;
import net.skyexcel.server.trade.data.PlayerInfo;
import net.skyexcel.server.trade.data.TargetInfo;
import net.skyexcel.server.trade.gui.TradeGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;


public class TradeListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            Inventory inv = event.getInventory();
        }
    }

    @EventHandler
    public void test(InventoryEvent event) {


    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void test(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        int slot = event.getSlot();
        
        if (inv != null) {

            if (event.getAction().equals(InventoryAction.PLACE_ALL)) {
                if (Data.playerInfo.containsKey(player.getUniqueId())) {


                    PlayerInfo playerInfo = Data.playerInfo.get(player.getUniqueId());
                    TargetInfo targetInfo = Data.targetInfo.get(playerInfo.getTarget().getUniqueId());

                    playerInfo.updateInventory(slot, inv, targetInfo.getInv());

                } else if (Data.targetInfo.containsKey(player.getUniqueId())) {
                    TargetInfo targetInfo = Data.targetInfo.get(player.getUniqueId());


                    if (slot == 10) {
                        targetInfo.setItem(16, event.getCurrentItem());
                    }

                }
            }


        }
    }


    @EventHandler
    public void onDrag(InventoryDragEvent event) {

        if (event.getWhoClicked() instanceof Player player) {
            @NotNull Set<Integer> slots = event.getRawSlots();
            Inventory inv = event.getInventory();
            player.sendMessage(slots + "");
        }
    }
}
