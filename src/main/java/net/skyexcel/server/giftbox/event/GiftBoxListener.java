package net.skyexcel.server.giftbox.event;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiftBoxListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId())) {
            GiftBox giftBox = GiftBoxData.giftBoxHashMap.get(player.getUniqueId());
            int slot = event.getSlot();
            List<Integer> slots =Arrays.stream(giftBox.black_glass).boxed().toList();
            if(slots.contains(slot)){
                event.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event){
        Player player = (Player) event.getWhoClicked();
        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId())) {

        }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getPlayer();

        if (GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId())) {
            GiftBox giftBox = GiftBoxData.giftBoxHashMap.get(player.getUniqueId());

            if(inv.equals(giftBox.getInv())){
                giftBox.save(inv);
                GiftBoxData.giftBoxHashMap.remove(player.getUniqueId());
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(GiftBoxData.giftBoxHashMap.containsKey(player.getUniqueId()))
            GiftBoxData.giftBoxHashMap.remove(player.getUniqueId());
    }
}