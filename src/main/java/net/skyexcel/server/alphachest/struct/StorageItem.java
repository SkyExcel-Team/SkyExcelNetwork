package net.skyexcel.server.alphachest.struct;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class StorageItem {

    private String name;

    private Material material;


    public StorageItem() {
        name = "가상창고권";
        material = Material.PAPER;
    }


    public ItemStack addItem() {
        ItemStack item = new ItemStack(material, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(List.of("§7우클릭시 가상창고를 한개 얻습니다!"));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(List.of("§7우클릭시 가상창고를 한개 얻습니다!"));
        item.setItemMeta(meta);

        return item;
    }
}
