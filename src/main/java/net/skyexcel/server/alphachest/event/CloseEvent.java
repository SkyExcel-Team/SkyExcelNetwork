package net.skyexcel.server.alphachest.event;

import net.skyexcel.server.alphachest.struct.CashStorage;
import net.skyexcel.server.alphachest.struct.Storage;
import net.skyexcel.server.alphachest.struct.StorageData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

public class CloseEvent implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (StorageData.storageHashMap.containsKey(player.getUniqueId())) {
            Storage storage = StorageData.storageHashMap.get(player.getUniqueId());
            storage.saveStorage();
        } else if (StorageData.cashStorageHashMap.containsKey(player.getUniqueId())) {
            CashStorage storage = StorageData.cashStorageHashMap.get(player.getUniqueId());
            storage.saveStorage();
        }
    }
}
