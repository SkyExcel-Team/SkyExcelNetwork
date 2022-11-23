package net.skyexcel.server.alphachest.event;

import net.skyexcel.server.alphachest.struct.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class StorageListener implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        EquipmentSlot slot = event.getHand();

        if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            assert slot != null;

            if (!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {

                StorageItem testItem = new StorageItem();

                CashStorageItem cashStorageItem = new CashStorageItem();
                ItemStack item = player.getInventory().getItemInMainHand();

                if (item.isSimilar(testItem.getItemStack())) {
                    Storage storage = new Storage(player);
                    storage.setIndex(player, item);
                } else if (item.isSimilar(cashStorageItem.getItemStack())) {
                    CashStorage storage = new CashStorage(player);
                    storage.setIndex(player, item);
                }
            }
        }
    }
}
