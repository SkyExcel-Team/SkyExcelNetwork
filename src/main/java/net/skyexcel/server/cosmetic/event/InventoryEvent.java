package net.skyexcel.server.cosmetic.event;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class InventoryEvent implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked().isOp()) return;

        if (e.getCurrentItem() == null) return;
        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getCurrentItem().getType()) && e.getCurrentItem().getItemMeta().hasCustomModelData()) {
            e.setCancelled(true);
            e.setCursor(null);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        if (e.getPlayer().isOp()) return;
        if (e.getMainHandItem() == null && e.getOffHandItem() == null) return;

        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getMainHandItem().getType()) && e.getMainHandItem().getItemMeta().hasCustomModelData()) {
            e.setCancelled(true);
        } else if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getOffHandItem().getType()) && e.getOffHandItem().getItemMeta().hasCustomModelData()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getWhoClicked().isOp()) return;

        Map<Integer, ItemStack> newItems = e.getNewItems();

        newItems.forEach((key, value) -> {
            if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(value.getType()) && value.getItemMeta().hasCustomModelData()) {
                e.setCancelled(true);
                e.setCursor(null);
            }
        });
    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent e) {
        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getItem().getType()) && e.getItem().getItemMeta().hasCustomModelData()) {
            e.setCancelled(true);
        }
    }
}
