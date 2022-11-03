package net.skyexcel.server.menu.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.List;


public class Item {

    public static ItemStack playerSkull(String name, String display, List<String> lore) {
        OfflinePlayer owner = Bukkit.getOfflinePlayer(name);


        ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);

        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);

        meta.setOwner(owner.getName());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',display));
        meta.setLore(Translate.translate(lore,owner.getPlayer()));
        stack.setItemMeta(meta);
        return stack;
    }

}
