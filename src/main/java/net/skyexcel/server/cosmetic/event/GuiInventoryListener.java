package net.skyexcel.server.cosmetic.event;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiInventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (!SkyExcelNetworkCosmeticMain.guiUtil.guiMap.containsKey(player))
            return;

        e.setCancelled(true);
        e.setCursor(null);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (!SkyExcelNetworkCosmeticMain.guiUtil.guiMap.containsKey(player))
            return;

        SkyExcelNetworkCosmeticMain.guiUtil.guiMap.remove(player);
    }
}
