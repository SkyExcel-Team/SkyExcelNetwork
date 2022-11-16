package net.skyexcel.server.job.data.stat;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Bait {


    public void run(Player player, ItemStack item, ItemStack previous) {
        if (item != null) {
            if (item.getType().equals(Material.FISHING_ROD)) {
                if (item.getEnchantmentLevel(Enchantment.LURE) == 0) {
                    item.addUnsafeEnchantment(Enchantment.LURE, 1);
                } else {
                    item.addUnsafeEnchantment(Enchantment.LURE, item.getEnchantmentLevel(Enchantment.LURE) + 1);
                }
            }
        } else {

            if (previous != null || previous.getType().equals(Material.FISHING_ROD)) {
                if (previous.getEnchantmentLevel(Enchantment.LURE) == 0) {
                    previous.addUnsafeEnchantment(Enchantment.LURE, 1);
                } else {
                    previous.addUnsafeEnchantment(Enchantment.LURE, item.getEnchantmentLevel(Enchantment.LURE) + 1);
                }
            }
        }
    }
}
