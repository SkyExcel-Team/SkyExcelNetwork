package net.skyexcel.server.seconomy.item;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum RandomPick {
    FIRST(0, 10000, 0),
    SECOND(10000, 100000, 0);

    @Getter
    private int MIN;

    @Getter
    private int MAX;

    @Getter
    private int customModelData;


    private final Material material = Material.PAPER;

    @Getter
    private final String DisplayName = "돈 렌덤뽑기";

    RandomPick(int min, int max, int customModelData) {
        MIN = min;
        MAX = max;

        this.customModelData = customModelData;
    }

    public ItemStack itemStack(int amount ){
        ItemStack itemStack = new ItemStack(material,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(DisplayName);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
