package net.skyexcel.server.cashshop.event;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.data.Cash;
import net.skyexcel.server.cashshop.data.CashShop;
import net.skyexcel.server.cashshop.data.CashShopData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class CashListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Cash cash = new Cash(player);

        if (cash.getAmount() == -1) {
            cash.setAmount(0);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (CashShopData.shop.containsKey(player.getUniqueId())) {
                CashShop cashShop = CashShopData.shop.get(player.getUniqueId());
                int slot = event.getSlot();

                Inventory inv = event.getClickedInventory();

                if (inv != null) {
                    if (inv.equals(cashShop.getEditGUI())) {
                        if (event.isLeftClick()) {
                            cashShop.setType(CashShop.Type.SELL);
                            player.closeInventory();
                        } else if (event.isRightClick()) {
                            cashShop.setType(CashShop.Type.BUY);
                            player.closeInventory();
                        }
                        event.setCancelled(true);
                    } else {
                        if (cashShop.getType().equals(CashShop.Type.OPEN)) {
                            if (event.isRightClick()) {
                                cashShop.purchase(player, slot);
                            } else if (event.isLeftClick()) {

                            }

                            event.setCancelled(true);
                        } else if (cashShop.getType().equals(CashShop.Type.EDIT)) {
                            if (event.isShiftClick()) {
                                cashShop.setSlot(slot);
                                cashShop.setType(CashShop.Type.SET);
                                cashShop.editGUI(player);
                                cashShop.setItemStack(event.getCurrentItem());
                                event.setCancelled(true);
                            }
                        }

                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            if (CashShopData.shop.containsKey(player.getUniqueId())) {
                CashShop cashShop = CashShopData.shop.get(player.getUniqueId());

                switch (cashShop.getType()) {
                    case SET -> {
                        if (event.getInventory().equals(cashShop.getEditGUI())) {
                            Bukkit.getScheduler().runTaskLater(SkyExcelNetworkCashShopMain.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    cashShop.load(player);
                                }
                            }, 0);
                        }
                    }
                    case SELL -> {
                        player.sendMessage("판매 가격을 입력 해 주세요!");
                    }
                    case BUY -> {
                        player.sendMessage("구매 가격을 입력 해 주세요!");
                    }
                    case CLOSE, DEFAULT, EDIT -> {
                        cashShop.save(event.getInventory());
                        CashShopData.shop.remove(player.getUniqueId());
                    }
                    case OPEN -> {
                        CashShopData.shop.remove(player.getUniqueId());
                    }
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (CashShopData.shop.containsKey(player.getUniqueId())) {
            try {
                String finalMessage = message;
                Bukkit.getScheduler().runTaskLater(SkyExcelNetworkCashShopMain.plugin, new Runnable() {
                    @Override
                    public void run() {
                        long amount = Long.parseLong(finalMessage);
                        CashShop cashShop = CashShopData.shop.get(player.getUniqueId());
                        cashShop.setAmount(amount);
                        cashShop.setType(CashShop.Type.EDIT);
                        cashShop.load(player);
                    }
                }, 0);

            } catch (NumberFormatException e) {
                player.sendMessage("정수를 입력해 주세요!");
            }
        }
    }
}
