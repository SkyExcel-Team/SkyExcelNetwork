package net.skyexcel.server.mileage.event;

import net.skyexcel.server.mileage.data.Data;
import net.skyexcel.server.mileage.data.shop.MileageShop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import skyexcel.data.file.util.Stockable;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        Data data = new Data();

        MileageShop shop = data.get(player);
        if (shop != null) {
//            if (shop.getType().equals(Stockable.SetType.SELL)) {
//                shop.setSell(Integer.parseInt(message));
//            } else if (shop.getType().equals(Stockable.SetType.BUY)) {
//                shop.setBuy(Integer.parseInt(message));
//            }
        }
    }
}
