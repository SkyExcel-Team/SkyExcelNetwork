package net.skyexcel.server.trade.event;

import net.skyexcel.server.trade.data.Data;
import net.skyexcel.server.trade.data.TradeGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClickEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void test(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        int slot = event.getSlot();

        if (Data.tradeGui.containsKey(player.getUniqueId())) {

            TradeGUI tradeGUI = Data.tradeGui.get(player.getUniqueId());

            assert inv != null;
            if (inv.equals(tradeGUI.getInv())) {
                if (player.equals(tradeGUI.getPlayer())) {
                    if (!equalSlot(tradeGUI.getTargetSlots(), slot)) {
                        event.setCancelled(true);
                    }
                } else if (player.equals(tradeGUI.getTarget())) {
                    if (!equalSlot(tradeGUI.getPlayerSlots(), slot)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {

        if(event.getWhoClicked() instanceof Player player){
            @NotNull Set<Integer> slots = event.getRawSlots();
            if (Data.tradeGui.containsKey(player.getUniqueId())) {
                TradeGUI tradeGUI = Data.tradeGui.get(player.getUniqueId());
                if (player.equals(tradeGUI.getPlayer())) {
                    for(int slot : slots){
                        if(!equalSlot(tradeGUI.getTargetSlots(),slot)){
                            event.setCancelled(true);
                        }
                    }
                } else if (player.equals(tradeGUI.getPlayer())) {
                    for(int slot : slots){
                        if(!equalSlot(tradeGUI.getPlayerSlots(),slot)){
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    public boolean equalSlot(int[] Slots, int Slot) {
        List<Integer> newSlots = new ArrayList<>();
        for (int slots : Slots) {
            newSlots.add(slots);
        }
        return newSlots.contains(Slot);
    }
}
