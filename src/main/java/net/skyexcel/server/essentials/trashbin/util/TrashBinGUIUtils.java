package net.skyexcel.server.essentials.trashbin.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrashBinGUIUtils {
    public static void openTrashBin(Player player, int lines, String title) {
        Inventory inv = Bukkit.createInventory(player, lines * 9, title);

        player.openInventory(inv);

        player.sendMessage("쓰레기통을 열고있습니다..");
    }
}
