package net.skyexcel.server.essentials.trashbin.util;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrashBinGUIUtils {
    public static void openTrashBin(Player player) {
        Inventory inv = Bukkit.createInventory(player,
                SkyExcelNetworkEssentialsMain.config.getInteger("trash_bin.lines") * 9,
                SkyExcelNetworkEssentialsMain.config.getString("trash_bin.title").replace("%player%", player.getDisplayName()));

        player.sendMessage("쓰레기통을 열고있습니다..");
        player.openInventory(inv);
    }
}
