package net.skyexcel.server.cashshop.event;

import com.google.common.base.Preconditions;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.data.Cash;
import net.skyexcel.server.cashshop.data.CashShop;
import net.skyexcel.server.cashshop.data.CashShopData;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class CashListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Cash cash = new Cash(player);

        if (cash.getLong() == -1) {
            cash.setAmount(0);
        }
        Items.newItem(ChatColor.GREEN + "test", Material.CHAIN, 1, List.of("테스트", "로어", "임", "ㅎㅇ", "ㅎㅎ"), 1, player.getInventory());
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
                        if (event.isRightClick()) {
                            cashShop.setType(CashShop.Type.SELL);
                            player.closeInventory();
                        } else if (event.isLeftClick()) {
                            cashShop.setType(CashShop.Type.BUY);
                            player.closeInventory();
                        }
                        event.setCancelled(true);
                    } else {
                        if (cashShop.getType().equals(CashShop.Type.OPEN)) {
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
                        } else if (cashShop.getType().equals(CashShop.Type.EDIT)) {
                            if (event.getClickedInventory().equals(event.getView().getTopInventory())) { // 클릭한 인벤토리가 아래일 경우, 해당 아이템의 로어는 사라진다,
                                if (event.getCurrentItem() != null) {
                                    if (event.isShiftClick()) {
                                        cashShop.setSlot(slot);
                                        cashShop.setType(CashShop.Type.SET);
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
        if (CashShopData.shop.containsKey(player.getUniqueId())) {
            CashShop cashShop = CashShopData.shop.get(player.getUniqueId());
            cashShop.removeLore(item);
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
