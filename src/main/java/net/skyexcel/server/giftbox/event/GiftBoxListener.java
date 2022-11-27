package net.skyexcel.server.giftbox.event;

import net.skyexcel.server.giftbox.data.CloseType;
import net.skyexcel.server.giftbox.data.GiftBox;
import net.skyexcel.server.giftbox.data.GiftBoxData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class GiftBoxListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId())) {
            GiftBox giftBox = GiftBoxData.giftBoxHashMap.get(player.getUniqueId());
            int slot = event.getSlot();

            if (giftBox.getNextButton() == slot) {
                giftBox.setCloseType(CloseType.NEXT_PAGE);
                giftBox.nextPage(player);
            } else if (giftBox.getPreviousButton() == slot) {
                giftBox.setCloseType(CloseType.PREVIOUS_PAGE);

            } else if (giftBox.getMailBox() == slot) {

            } else if (giftBox.getCheckBox() == slot) {
                giftBox.receiveReward(player, event.getClickedInventory());
            }

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId())) {
            GiftBoxData.giftBoxHashMap.remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getPlayer();

        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId())) {
            GiftBox giftBox = GiftBoxData.giftBoxHashMap.get(player.getUniqueId());
            if (giftBox.getCloseType().equals(CloseType.CLOSE)) {
                giftBox.saveInventory(inv);
                GiftBoxData.giftBoxHashMap.remove(player.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId()))
            GiftBoxData.giftBoxHashMap.remove(player.getUniqueId());
    }
}
