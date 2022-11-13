package net.skyexcel.server.mileage.event;

import net.skyexcel.server.mileage.data.Data;
import net.skyexcel.server.mileage.data.shop.MileageShop;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class CloseEvent implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        HumanEntity var3 = event.getPlayer();
        if (var3 instanceof Player player) {
            Inventory inv = event.getInventory();
            Data data = new Data();

            MileageShop shop = data.get(player);

            if (shop != null) {
//                switch (shop.getType()) {
//                    case BUY -> player.sendMessage("판매 가격을 입력 해 주세요!");
//                    case CLOSE -> {
//                        shop.save(inv);
//                        player.sendMessage("편집을 종료 하였습니다!");
//                    }
//                    case SELL -> player.sendMessage("구매 가격을 입력 해 주세요!");
//                }
            }

        }


    }
}
