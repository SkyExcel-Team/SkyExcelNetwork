package net.skyexcel.server.event;

import net.skyexcel.server.data.Data;
import net.skyexcel.server.data.TradeGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class closeEvent implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();


        if (Data.tradeGui.containsKey(player.getUniqueId())) {
            TradeGUI trade = Data.tradeGui.get(player.getUniqueId());

//            trade.getTarget().sendMessage("상대방이 거래를 취소 하였습니다! ");
//
//            trade.getTarget().getInventory().close();
        }
    }
}
