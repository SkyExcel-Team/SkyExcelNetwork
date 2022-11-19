package net.skyexcel.server.regionafk.event;


import net.skyexcel.server.regionafk.SkyExcelNetworkAFKMain;
import net.skyexcel.server.regionafk.data.AFK;

import net.skyexcel.server.regionafk.data.AFKData;
import net.skyexcel.server.regionafk.data.AFKShop;
import net.skyexcel.server.cashshop.data.StringData;
import org.bukkit.Bukkit;


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

public class AFKListener implements Listener {


    StringData stringData = new StringData();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        AFK AFK = new AFK(player);

        if (AFK.getLong() == -1) {
            AFK.setAmount(0);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (AFKData.shop.containsKey(player.getUniqueId())) {
                AFKShop AFKShop = AFKData.shop.get(player.getUniqueId());
                int slot = event.getSlot();

                Inventory inv = event.getClickedInventory();

                if (inv != null) {
                    if (inv.equals(AFKShop.getEditGUI())) {
                        if (event.isRightClick()) {
                            AFKShop.setType(net.skyexcel.server.regionafk.data.AFKShop.Type.SELL);
                            player.closeInventory();
                        } else if (event.isLeftClick()) {
                            AFKShop.setType(net.skyexcel.server.regionafk.data.AFKShop.Type.BUY);
                            player.closeInventory();
                        }
                        event.setCancelled(true);
                    } else {
                        if (AFKShop.getType().equals(net.skyexcel.server.regionafk.data.AFKShop.Type.OPEN)) {
                            if (event.isLeftClick()) {
                                if (event.isShiftClick()) {
                                    AFKShop.purchase(player, slot, 64);
                                } else {
                                    AFKShop.purchase(player, slot, 1);
                                }
                            } else if (event.isRightClick()) {
                                if (event.isShiftClick()) {
                                    AFKShop.sell(player, slot, 64);
                                } else {
                                    AFKShop.sell(player, slot, 1);
                                }
                            }
                            event.setCancelled(true);
                        } else if (AFKShop.getType().equals(net.skyexcel.server.regionafk.data.AFKShop.Type.EDIT)) {
                            if (event.getClickedInventory().equals(event.getView().getTopInventory())) { // 클릭한 인벤토리가 아래일 경우, 해당 아이템의 로어는 사라진다,
                                if (event.getCurrentItem() != null) {
                                    if (event.isShiftClick()) {
                                        AFKShop.setSlot(slot);
                                        AFKShop.setType(net.skyexcel.server.regionafk.data.AFKShop.Type.SET);
                                        AFKShop.editGUI(player);

                                        AFKShop.setItemStack(event.getCurrentItem());
                                        event.setCancelled(true);
                                    }
                                }
                            } else {
                                // 상점에서 아이템을 제거 했을 경우, 로어와 기타 데이터 정보를 초기화 시켜준다.
                                AFKShop.removeLore(event.getCursor());
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
        if (AFKData.shop.containsKey(player.getUniqueId())) {
            AFKShop AFKShop = AFKData.shop.get(player.getUniqueId());
            AFKShop.removeLore(item);
        }
    }


    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            if (AFKData.shop.containsKey(player.getUniqueId())) {
                AFKShop AFKShop = AFKData.shop.get(player.getUniqueId());

                switch (AFKShop.getType()) {
                    case SET -> {
                        if (event.getInventory().equals(AFKShop.getEditGUI())) {
                            Bukkit.getScheduler().runTaskLater(SkyExcelNetworkAFKMain.getPlugin(), new Runnable() {
                                @Override
                                public void run() {
                                    AFKShop.load(player);
                                }
                            }, 0);
                        }
                    }
                    case SELL -> {
                        player.sendMessage(stringData.settingSellPrice());
                    }
                    case BUY -> {
                        player.sendMessage(stringData.settingBuyPrice());
                    }
                    case CLOSE, DEFAULT, EDIT -> {
                        System.out.println(event.getInventory());
                        AFKShop.save(event.getInventory());
                        AFKData.shop.remove(player.getUniqueId());
                    }
                    case OPEN -> {
                        AFKData.shop.remove(player.getUniqueId());
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (AFKData.shop.containsKey(player.getUniqueId())) {
            try {
                String finalMessage = message;
                Bukkit.getScheduler().runTaskLater(SkyExcelNetworkAFKMain.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        long amount = Long.parseLong(finalMessage);
                        AFKShop AFKShop = AFKData.shop.get(player.getUniqueId());
                        AFKShop.setAmount(amount);
                        AFKShop.setType(net.skyexcel.server.regionafk.data.AFKShop.Type.EDIT);
                        AFKShop.load(player);
                    }
                }, 0);
            } catch (NumberFormatException e) {
                player.sendMessage("强 정수의 값을 입력해 주세요!");
            }
        }
    }
}
