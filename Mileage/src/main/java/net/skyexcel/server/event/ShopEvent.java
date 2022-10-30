package net.skyexcel.server.event;

import net.skyexcel.server.data.Data;
import net.skyexcel.server.data.StringData;
import net.skyexcel.server.data.shop.EditGUI;
import net.skyexcel.server.data.shop.SettingType;
import net.skyexcel.server.data.shop.Shop;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;

public class ShopEvent implements Listener {


    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Inventory inv = event.getInventory();
        Player player = (Player) event.getPlayer();
        String title = event.getView().getTitle();


        if (player.isOp()) {
            if (Data.shop.containsKey(player.getUniqueId())) {

                Shop shop = Data.shop.get(player.getUniqueId());
                shop.save(inv);
                Data.shop.remove(player.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();

        if (Data.shop.containsKey(player.getUniqueId())) {
            Shop shop = Data.shop.get(player.getUniqueId());
            player.sendMessage(StringData.gui_title() + " " + title);
            if (title.equalsIgnoreCase(StringData.gui_title())) {
                if (event.getSlot() == StringData.buy_sell_slot()) {
                    if (event.getClick().isLeftClick()) {
                        shop.setType(SettingType.BUY);
                        player.sendMessage("구매 가격을 설정 해 주세요!");
                    } else if (event.getClick().isRightClick()) {
                        shop.setType(SettingType.SELL);
                        player.sendMessage("판매 가격을 설정 해 주세요!");
                    }
                    player.closeInventory();
                }
                event.setCancelled(true);
            } else {
                if (event.isShiftClick()) {
                    shop.setSlot(event.getSlot());

                    EditGUI edit = new EditGUI();
                    edit.open(player);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (Data.shop.containsKey(player.getUniqueId())) {
            Shop shop = Data.shop.get(player.getUniqueId());
            try {
                long price = Long.parseLong(message);
                shop.setBuyPrice(price);
                player.sendMessage("가격을 설정했습니다!");
                shop.load(player);
            } catch (NumberFormatException e) {
                player.sendMessage("정수를 입력해 주세요!");
            }
        }
    }
}
