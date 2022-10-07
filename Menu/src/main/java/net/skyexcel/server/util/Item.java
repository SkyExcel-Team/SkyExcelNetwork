package net.skyexcel.server.util;

import org.bukkit.Bukkit;
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
        meta.setDisplayName(display);
        meta.setLore(Translate.translate(lore));
        stack.setItemMeta(meta);
        return stack;
    }
}
