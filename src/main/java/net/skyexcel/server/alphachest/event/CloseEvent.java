package net.skyexcel.server.alphachest.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

public class CloseEvent implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        String title = e.getView().getTitle();

        if (title.startsWith("님의 가상창고", title.length() - 11)) {
            Config config = new Config("data/storages/" + p.getUniqueId());
            GUI gui = new GUI(config);

            gui.saveInventory(title.substring(title.length() - 1, title.length() - 1), e.getInventory());
        }
    }
}
