package net.skyexcel.server.event;

import net.skyexcel.server.data.SkyBlockData;
import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.ui.gui.MaterialPageMember;
import net.skyexcel.server.ui.gui.MaterialPagePartTime;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class banBlockEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);


        if (event.getClickedInventory() != null) {
            ItemStack item = event.getCurrentItem();
            if (item != null) {
                if (SkyBlockData.testPage.containsKey(player.getUniqueId())) {
                    MaterialPagePartTime gui = SkyBlockData.testPage.get(player.getUniqueId());
                    if (slot == gui.getNEXT_PAGE_SLOT()) {
                        gui.nextPage(player);
                    } else if (slot == gui.getPREVIOUS_PAGE_SLOT()) {
                        gui.previousPage(player);
                    } else {
                        gui.select(player, slot, event.isShiftClick());
                    }
                    event.setCancelled(true);
                } else if (SkyBlockData.memberPage.containsKey(player.getUniqueId())) {
                    MaterialPageMember gui = SkyBlockData.memberPage.get(player.getUniqueId());
                    if (slot == gui.getNEXT_PAGE_SLOT()) {
                        gui.nextPage(player,event.isShiftClick());
                    } else if (slot == gui.getPREVIOUS_PAGE_SLOT()) {
                        gui.previousPage(player, event.isShiftClick());
                    } else {
                        gui.select(player, slot, event.isShiftClick());
                    }
                    event.setCancelled(true);
                } else if (SkyBlockData.partTimePage.containsKey(player.getUniqueId())) {
                    MaterialPagePartTime gui = SkyBlockData.partTimePage.get(player.getUniqueId());
                    if (slot == gui.getNEXT_PAGE_SLOT()) {
                        gui.nextPage(player);
                    } else if (slot == gui.getPREVIOUS_PAGE_SLOT()) {
                        gui.previousPage(player);
                    } else {
                        gui.select(player, slot, event.isShiftClick());
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();


        if (SkyBlockData.partTimePage.containsKey(player.getUniqueId())) {

            player.sendMessage("알바 밴블록 설정을 완료 하였습니다.");
            SkyBlockData.partTimePage.remove(player.getUniqueId());

        } else if (SkyBlockData.memberPage.containsKey(player.getUniqueId())) {
            player.sendMessage("맴버 밴블록 설정을 완료 하였습니다.");
            SkyBlockData.memberPage.remove(player.getUniqueId());
        }
    }
}
