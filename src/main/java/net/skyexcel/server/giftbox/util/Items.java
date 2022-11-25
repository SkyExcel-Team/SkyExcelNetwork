package net.skyexcel.server.giftbox.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class Items {
    public static void newItem(String name, Material type, int stack, List<String> lore, int loc, Inventory inv) {
        ItemStack item = new ItemStack(type, stack);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(loc, item);
    }

    public static void Enchant(ItemStack item, Inventory inv, int pos) {
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(item.getItemMeta().getLore());
        item.setItemMeta(meta);
        inv.setItem(pos, item);
    }

    public static void Enchant(ItemStack item, List<String> lore, Inventory inv, int pos) {
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(pos, item);
    }


}
