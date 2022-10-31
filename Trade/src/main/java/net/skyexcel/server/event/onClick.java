package net.skyexcel.server.event;

import net.skyexcel.server.data.Data;
import net.skyexcel.server.data.TradeGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class onClick implements Listener {

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

                    if (equalSlot(tradeGUI.getTargetSlots(), slot)) {
                        ItemStack item = inv.getItem(slot);
                        if (item != null) {
                            player.sendMessage("거긴 아니야 장애야");
                        }
                    }
                } else if (player.equals(tradeGUI.getTarget())) {

                    if (equalSlot(tradeGUI.getPlayerSlots(), slot)) {
                        player.sendMessage("test 섹스 ");
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
