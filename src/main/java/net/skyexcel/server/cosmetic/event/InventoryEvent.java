package net.skyexcel.server.cosmetic.event;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class InventoryEvent implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getWhoClicked().isOp()) return;

        if (e.getCurrentItem() == null) return;
        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getCurrentItem().getType()) && e.getCurrentItem().getItemMeta().hasCustomModelData()) {
            e.getWhoClicked().sendMessage("强 §c코스튬은 클릭할 수 없습니다!");
            e.setCancelled(true);

            e.setCursor(null);
            e.getWhoClicked().closeInventory();
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        if (e.getPlayer().isOp()) return;
        if (e.getMainHandItem() == null && e.getOffHandItem() == null) return;

        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getMainHandItem().getType()) && e.getMainHandItem().getItemMeta().hasCustomModelData()) {
            e.getPlayer().sendMessage("强 §c코스튬은 옮길 수 없습니다!");
            e.setCancelled(true);
        } else if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getOffHandItem().getType()) && e.getOffHandItem().getItemMeta().hasCustomModelData()) {
            e.getPlayer().sendMessage("强 §c코스튬은 옮길 수 없습니다!");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getPlayer().isOp()) return;

        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getItemDrop().getType()) && e.getItemDrop().getItemStack().getItemMeta().hasCustomModelData()) {
            e.getPlayer().sendMessage("强 §c코스튬은 버릴 수 없습니다!");
            e.getItemDrop().remove();
            e.setCancelled(true);
        } else if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getItemDrop().getType()) && e.getItemDrop().getItemStack().getItemMeta().hasCustomModelData()) {
            e.getPlayer().sendMessage("强 §c코스튬은 버릴 수 없습니다!");
            e.getItemDrop().remove();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent e) {
        if (e.getSource().getViewers().get(0).isOp()) return;

        if (List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(e.getItem().getType()) && e.getItem().getItemMeta().hasCustomModelData()) {
            e.getSource().getViewers().get(0).sendMessage("强 §c코스튬은 옮길 수 없습니다!");
            e.setCancelled(true);
        }
    }
}
