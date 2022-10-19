package net.skyexcel.server.event;

import net.skyexcel.server.data.SkyBlockData;
import net.skyexcel.server.ui.gui.MaterialPagePartTime;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class banBlockEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        String title = event.getView().getTitle();
        if (event.getClickedInventory() != null) {
            ItemStack item = event.getCurrentItem();
            if (item != null) {
                if (SkyBlockData.partTimePage.containsKey(player.getUniqueId())) {
                    MaterialPagePartTime material = SkyBlockData.partTimePage.get(player.getUniqueId());

                    if (title.equalsIgnoreCase(material.getTitle())) {
                        if (slot == material.getNEXT_PAGE_SLOT()) {
                            if (material.getCurrentPage() != 15)
                                material.increase(player);
                        } else if (slot == material.getPREVIOUS_PAGE_SLOT()) {
                            if (material.getCurrentPage() != 1)
                                material.decrease(player);
                        } else {
                            material.addBanBlock(item.getType(), player, event.getClickedInventory(), slot);
                        }
                        event.setCancelled(true);
                    }
                }
            }

        }
    }
}
