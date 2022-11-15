package net.skyexcel.server.seconomy.event;

import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import net.skyexcel.server.seconomy.data.SEConomy;
import net.skyexcel.server.seconomy.data.SEconomyShop;
import net.skyexcel.server.seconomy.data.SEConomyShopData;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SEConomyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SEConomy cash = new SEConomy(player);

        if (cash.getLong() == -1) {
            cash.setAmount(0);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (SEConomyShopData.shop.containsKey(player.getUniqueId())) {
                SEconomyShop cashShop = SEConomyShopData.shop.get(player.getUniqueId());
                int slot = event.getSlot();

                Inventory inv = event.getClickedInventory();

                if (inv != null) {
                    if (inv.equals(cashShop.getEditGUI())) {
                        if (event.isRightClick()) {
                            cashShop.setType(SEconomyShop.Type.SELL);
                            player.closeInventory();
                        } else if (event.isLeftClick()) {
                            cashShop.setType(SEconomyShop.Type.BUY);
                            player.closeInventory();
                        }
                        event.setCancelled(true);
                    } else {
                        if (cashShop.getType().equals(SEconomyShop.Type.OPEN)) {
                            if (event.isLeftClick()) {
                                if (event.isShiftClick()) {
                                    cashShop.purchase(player, slot, 64);
                                } else {
                                    cashShop.purchase(player, slot, 1);
                                }
                            } else if (event.isRightClick()) {
                                if (event.isShiftClick()) {
                                    cashShop.sell(player, slot, 64);
                                } else {
                                    cashShop.sell(player, slot, 1);
                                }
                            }
                            event.setCancelled(true);
                        } else if (cashShop.getType().equals(SEconomyShop.Type.EDIT)) {
                            if (event.getClickedInventory().equals(event.getView().getTopInventory())) { // 클릭한 인벤토리가 아래일 경우, 해당 아이템의 로어는 사라진다,
                                if (event.getCurrentItem() != null) {
                                    if (event.isShiftClick()) {
                                        cashShop.setSlot(slot);
                                        cashShop.setType(SEconomyShop.Type.SET);
                                        cashShop.editGUI(player);

                                        cashShop.setItemStack(event.getCurrentItem());
                                        event.setCancelled(true);
                                    }
                                }
                            } else {
                                // 상점에서 아이템을 제거 했을 경우, 로어와 기타 데이터 정보를 초기화 시켜준다.
                                cashShop.removeLore(event.getCursor());
                            }
                        }
                    }
                }
            }
        }
    }


    // 인벤토리에서 아이템을 떨궜을때에 로어와 데이터를 초기화 시켜 줍니다.
    @EventHandler
    public void dropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        Player player = event.getPlayer();
        if (SEConomyShopData.shop.containsKey(player.getUniqueId())) {
            SEconomyShop cashShop = SEConomyShopData.shop.get(player.getUniqueId());
            cashShop.removeLore(item);
        }
    }


    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            if (SEConomyShopData.shop.containsKey(player.getUniqueId())) {
                SEconomyShop cashShop = SEConomyShopData.shop.get(player.getUniqueId());

                switch (cashShop.getType()) {
                    case SET -> {
                        if (event.getInventory().equals(cashShop.getEditGUI())) {
                            Bukkit.getScheduler().runTaskLater(SkyExcelNetworkSEconomyMain.plugin, new Runnable() {
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
                        System.out.println(event.getInventory());
                        cashShop.save(event.getInventory());
                        SEConomyShopData.shop.remove(player.getUniqueId());
                    }
                    case OPEN -> {
                        SEConomyShopData.shop.remove(player.getUniqueId());
                    }
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (SEConomyShopData.shop.containsKey(player.getUniqueId())) {
            try {
                String finalMessage = message;
                Bukkit.getScheduler().runTaskLater(SkyExcelNetworkSEconomyMain.plugin, new Runnable() {
                    @Override
                    public void run() {
                        long amount = Long.parseLong(finalMessage);
                        SEconomyShop cashShop = SEConomyShopData.shop.get(player.getUniqueId());
                        cashShop.setAmount(amount);
                        cashShop.setType(SEconomyShop.Type.EDIT);
                        cashShop.load(player);
                    }
                }, 0);
            } catch (NumberFormatException e) {
                player.sendMessage("정수를 입력해 주세요!");
            }
        }
    }
}
