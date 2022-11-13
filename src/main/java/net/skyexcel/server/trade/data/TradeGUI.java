package net.skyexcel.server.trade.data;

import net.skyexcel.server.seconomy.InventoryUpdate;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import net.skyexcel.server.trade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class TradeGUI {

    private static Inventory inv;

    private Player player;
    private Player target;

    private final Decoration decoration;

    private Status status = Status.DURING;

    private final int[] playerSlots = {10, 11, 12,
            19, 20, 21,
            28, 29, 30,};

    private final int[] targetSlots = {14, 15, 16,
            23, 24, 25,
            32, 33, 34};

    private int statusSlot;


    public TradeGUI() {
        decoration = new Decoration();
    }


    public void setTarget(Player target) {
        this.target = target;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int[] getPlayerSlots() {
        return playerSlots;
    }

    public int[] getTargetSlots() {
        return targetSlots;
    }

    public void openGUI() {
        inv = Bukkit.createInventory(null, 54, StringData.title);

        Arrays.stream(decoration.black_glass).forEach(slots -> {
            Items.newItem("", Material.GRAY_STAINED_GLASS_PANE, 1, List.of(""), slots, inv);
        });

        Arrays.stream(decoration.IRON_BARS).forEach(slots -> {
            Items.newItem("", Material.IRON_BARS, 1, List.of(""), slots, inv);
        });

        Items.newItem("", Material.LIME_WOOL, 1, List.of(""), 10, inv);
        Items.newItem("", Material.LIME_WOOL, 1, List.of(""), decoration.PLAYER_ACCEPT, inv);
        Items.newItem("", Material.GRAY_DYE, 1, List.of(""), decoration.PLAYER_STATUS, inv);
        Items.newItem("", Material.RED_WOOL, 1, List.of(""), decoration.PLAYER_DENY, inv);

        Items.newItem("", Material.LIME_WOOL, 1, List.of(""), decoration.TARGET_ACCEPT, inv);
        Items.newItem("", Material.GRAY_DYE, 1, List.of(""), decoration.TARGET_STATUS, inv);
        Items.newItem("", Material.RED_WOOL, 1, List.of(""), decoration.TARGET_DENY, inv);

        player.openInventory(inv);
        target.openInventory(inv);
    }

    public void done() {
        Arrays.stream(playerSlots).forEach(slot -> {
            ItemStack item = inv.getItem(slot);
            if (item != null) {
                target.getInventory().addItem(item);
            }
        });

        Arrays.stream(targetSlots).forEach(slot -> {
            ItemStack item = inv.getItem(slot);

            if (item != null) {
                player.getInventory().addItem(item);
            }
        });
        player.sendMessage("거래가 완료 되었습니다!");
        target.sendMessage("거래가 완료 되었습니다!");
        player.closeInventory();
        target.closeInventory();
    }

    public Status getStatus() {
        return status;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }

    public Inventory getInv() {
        return inv;
    }

    public Decoration getDecoration() {
        return decoration;
    }

    public void setStatusSlot(int statusSlot) {
        this.statusSlot = statusSlot;
    }

    public void setStatus(Status status) {
        this.status = status;
        switch (status) {
            case DENY -> {

                Items.newItem("", Material.GRAY_DYE, 1, List.of(""), statusSlot, inv);
                updateTitle(StringData.title);

            }
            case ACCEPT -> {
                Items.newItem("", Material.LIME_DYE, 1, List.of(""), statusSlot, inv);
                updateTitle(StringData.Waiting);
            }
            case DURING, WAITING -> {

            }
        }
    }

    private void updateTitle(String title) {
        InventoryUpdate.updateInventory(SkyExcelNetworkTradeMain.plugin, player, title);
        InventoryUpdate.updateInventory(SkyExcelNetworkTradeMain.plugin, target, title);
    }

    public static class Decoration {
        final int[] black_glass = {0, 1, 2, 3, 4, 5, 6, 7, 8, 17, 9, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        final int[] IRON_BARS = {13, 22, 31, 40};

        final int PLAYER_ACCEPT = 37;
        final int PLAYER_STATUS = 38;
        final int PLAYER_DENY = 39;

        final int TARGET_ACCEPT = 41;
        final int TARGET_STATUS = 42;
        final int TARGET_DENY = 43;

        public int[] getBlack_glass() {
            return black_glass;
        }

        public int getPLAYER_ACCEPT() {
            return PLAYER_ACCEPT;
        }

        public int getPLAYER_DENY() {
            return PLAYER_DENY;
        }

        public int getPLAYER_STATUS() {
            return PLAYER_STATUS;
        }

        public int getTARGET_ACCEPT() {
            return TARGET_ACCEPT;
        }

        public int getTARGET_DENY() {
            return TARGET_DENY;
        }

        public int getTARGET_STATUS() {
            return TARGET_STATUS;
        }

        public int[] getIRON_BARS() {
            return IRON_BARS;
        }
    }

    public enum Status {
        DENY, ACCEPT, DURING, WAITING
    }
}
