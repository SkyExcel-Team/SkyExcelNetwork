package net.skyexcel.server.trade.gui;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.giftbox.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.List;

public class TradeGUI {

    private Player player;


    private Inventory inv;

    final List<Integer> BLACK_GLASS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 17, 9, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53);

    final List<Integer> IRON_BARS = List.of(13, 22, 31, 40);

    private Player target;

    final int PLAYER_ACCEPT = 37;
    final int PLAYER_STATUS = 38;
    final int PLAYER_DENY = 39;

    final int TARGET_ACCEPT = 41;
    final int TARGET_STATUS = 42;
    final int TARGET_DENY = 43;


    private Items items = new Items();


    public TradeGUI(Player player) {
        this.player = player;
    }

    public void open() {
        Inventory inv = Bukkit.createInventory(null, 54, "거래");

        BLACK_GLASS.forEach(slots -> {
            items.newItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1, List.of(""), slots, inv);
        });

        IRON_BARS.forEach(slots -> {
            items.newItem(" ", Material.IRON_BARS, 1, List.of(""), slots, inv);
        });

        items.newItem("준비", Material.LIME_WOOL, 1, List.of(""), PLAYER_ACCEPT, inv);
        items.newItem("", Material.GRAY_DYE, 1, List.of(""), PLAYER_STATUS, inv);
        items.newItem("", Material.RED_WOOL, 1, List.of(""), PLAYER_DENY, inv);

        items.newItem("", Material.LIME_WOOL, 1, List.of(""), TARGET_ACCEPT, inv);
        items.newItem("", Material.GRAY_DYE, 1, List.of(""), TARGET_STATUS, inv);
        items.newItem("", Material.RED_WOOL, 1, List.of(""), TARGET_DENY, inv);
        this.inv = inv;
        player.openInventory(inv);
    }

    public void updateInventory() {

    }

    public void setItem(int slot, ItemStack itemStack) {
        inv.setItem(slot, itemStack);
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public List<Integer> getIRON_BARS() {
        return IRON_BARS;
    }

    public int getTARGET_STATUS() {
        return TARGET_STATUS;
    }

    public int getTARGET_DENY() {
        return TARGET_DENY;
    }

    public int getTARGET_ACCEPT() {
        return TARGET_ACCEPT;
    }

    public int getPLAYER_STATUS() {
        return PLAYER_STATUS;
    }

    public int getPLAYER_DENY() {
        return PLAYER_DENY;
    }

    public int getPLAYER_ACCEPT() {
        return PLAYER_ACCEPT;
    }

    public List<Integer> getBLACK_GLASS() {
        return BLACK_GLASS;
    }

    public Inventory getInv() {
        return inv;
    }

    /**
     * 상대방 인벤토리에 자신의 인벤토리를 불러오게 시키는 메소드.
     *
     * @param slots 옮겨질 슬롯 위치
     * @param inv   자신의 인벤토리
     */
    public void updateInventory(int slots, Inventory inv, Inventory target) {
        for (int i : List.of(10, 11, 12, 19, 20, 21, 28, 29, 30)) {
            if (slots == i) {
                ItemStack items = inv.getItem(i);
                if (items != null) {
                    System.out.println(items);
                    int newSlot = i + 4;
                    target.setItem(newSlot, items);
                }
            }

        }
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }
}
