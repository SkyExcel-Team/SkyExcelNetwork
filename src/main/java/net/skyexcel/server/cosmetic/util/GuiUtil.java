package net.skyexcel.server.cosmetic.util;

import net.skyexcel.server.cosmetic.data.CosmeticType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiUtil {
    public void openGui(Player player, CosmeticType type) {
        Inventory inv = Bukkit.createInventory(null, 54, type.name());

        if (type == CosmeticType.BACK) {

        } else if (type == CosmeticType.HAT) {

        } else if (type == CosmeticType.OFFHAND) {

        }
    }
}
