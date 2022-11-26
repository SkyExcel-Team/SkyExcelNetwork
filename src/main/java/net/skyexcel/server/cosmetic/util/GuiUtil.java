package net.skyexcel.server.cosmetic.util;

import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.CosmeticType;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiUtil {
    public final Map<Player, CosmeticType> guiMap = new HashMap<>();

    public void openGui(Player player, CosmeticType type) {
        Inventory inv = Bukkit.createInventory(null, 54, type.name());

        if (type == CosmeticType.BACK) {
            List<Cosmetic.BACK> ownCosmetics = new ArrayList<>(new PlayerCosmeticData(player).getBackCosmetics());

            for (Cosmetic.BACK cosmetic : Cosmetic.BACK.values()) {
                if (cosmetic == Cosmetic.BACK.NONE) continue;

                ItemStack item = new ItemStack(cosmetic.getType(), 1);
                ItemMeta meta = item.getItemMeta();
                meta.setCustomModelData(cosmetic.getCustomModelData());
                meta.setDisplayName(cosmetic.getName() + " 치장");

                List<String> lore = new ArrayList<>(List.of("==[ 치장 아이템 정보 ]==",
                        "분류 : 등", "이름 : " + cosmetic.getName(),
                        "", ownCosmetics.contains(cosmetic) ? "UNLOCKED" : "LOCKED"));
                if (ownCosmetics.contains(cosmetic))
                    lore.add("우클릭 : 장착");

                meta.setLore(lore);
                item.setItemMeta(meta);

                inv.addItem(item);
            }
        } else if (type == CosmeticType.HAT) {
            List<Cosmetic.HAT> ownCosmetics = new ArrayList<>(new PlayerCosmeticData(player).getHatCosmetics());

            for (Cosmetic.HAT cosmetic : Cosmetic.HAT.values()) {
                if (cosmetic == Cosmetic.HAT.NONE) continue;

                ItemStack item = new ItemStack(cosmetic.getType(), 1);
                ItemMeta meta = item.getItemMeta();
                meta.setCustomModelData(cosmetic.getCustomModelData());
                meta.setDisplayName(cosmetic.getName() + " 치장");

                List<String> lore = new ArrayList<>(List.of("==[ 치장 아이템 정보 ]==",
                        "분류 : 모자", "이름 : " + cosmetic.getName(),
                        "", ownCosmetics.contains(cosmetic) ? "UNLOCKED" : "LOCKED"));
                if (ownCosmetics.contains(cosmetic))
                    lore.add("우클릭 : 장착");

                meta.setLore(lore);
                item.setItemMeta(meta);

                inv.addItem(item);
            }
        } else if (type == CosmeticType.OFFHAND) {
            List<Cosmetic.OFFHAND> ownCosmetics = new ArrayList<>(new PlayerCosmeticData(player).getOffhandCosmetics());

            for (Cosmetic.OFFHAND cosmetic : Cosmetic.OFFHAND.values()) {
                if (cosmetic == Cosmetic.OFFHAND.NONE) continue;

                ItemStack item = new ItemStack(cosmetic.getType(), 1);
                ItemMeta meta = item.getItemMeta();
                meta.setCustomModelData(cosmetic.getCustomModelData());
                meta.setDisplayName(cosmetic.getName() + " 치장");

                List<String> lore = new ArrayList<>(List.of("==[ 치장 아이템 정보 ]==",
                        "분류 : 등", "이름 : " + cosmetic.getName(),
                        "", ownCosmetics.contains(cosmetic) ? "UNLOCKED" : "LOCKED"));
                if (ownCosmetics.contains(cosmetic))
                    lore.add("우클릭 : 장착");

                meta.setLore(lore);
                item.setItemMeta(meta);

                inv.addItem(item);
            }
        }

        guiMap.put(player, type);
        player.openInventory(inv);
    }
}
