package net.skyexcel.server.trade.event;

import net.skyexcel.server.trade.data.Data;
import net.skyexcel.server.trade.data.TradeGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onClick implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        int slot = event.getSlot();

        if (Data.tradeGui.containsKey(player.getUniqueId())) {
            TradeGUI trade = Data.tradeGui.get(player.getUniqueId());

            assert inv != null;
            if (inv.equals(trade.getInv())) {
                if (player.equals(trade.getPlayer())) {

                    if (equals(trade, slot)) {
                        event.setCancelled(true);
                    } else if (slot == trade.getDecoration().getPLAYER_ACCEPT()) {
                        trade.setStatusSlot(trade.getDecoration().getPLAYER_STATUS());
                        trade.setStatus(TradeGUI.Status.ACCEPT);

                        if (acceptBoth(inv, trade.getDecoration().getTARGET_STATUS())) {
                            trade.done();
                        }

                    } else if (slot == trade.getDecoration().getPLAYER_DENY()) {
                        trade.setStatusSlot(trade.getDecoration().getPLAYER_STATUS());
                        trade.setStatus(TradeGUI.Status.DENY);
                    }
                } else if (player.equals(trade.getTarget())) {

                    if (slot == trade.getDecoration().getTARGET_ACCEPT()) {
                        trade.setStatusSlot(trade.getDecoration().getTARGET_STATUS());
                        trade.setStatus(TradeGUI.Status.ACCEPT);

                        if (acceptBoth(inv, trade.getDecoration().getPLAYER_STATUS())) {
                            trade.done();
                        }

                    } else if (slot == trade.getDecoration().getTARGET_DENY()) {
                        trade.setStatusSlot(trade.getDecoration().getTARGET_STATUS());
                        trade.setStatus(TradeGUI.Status.DENY);
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    public void cancel(int slot, int value, InventoryClickEvent event) {

        if (slot == value) {
            event.setCancelled(true);
        }
    }

    public boolean acceptBoth(Inventory inv, int slot) {
        ItemStack item = inv.getItem(slot);
        assert item != null;
        return item.getType().equals(Material.LIME_DYE);
    }

    public boolean equals(TradeGUI trade, int slot) {
        for (int i : trade.getPlayerSlots()) {
            return slot != i;
        }
        return false;
    }

    public boolean equalSlot(int slot, int[] slots) {
        for (int i = 0; i < slots.length; i++) {
            System.out.println(i);
            return slot == i;
        }
        return false;
    }
}
