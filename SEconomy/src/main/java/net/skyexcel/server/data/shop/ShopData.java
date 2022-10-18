package net.skyexcel.server.data.shop;

import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.data.NBTData;
import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import skyexcel.data.file.Config;

public class ShopData {

    private String name;


    private Config config;

    public ShopData(String name) {
        this.name = name;
        config = new Config("shop/" + name);
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void create(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "");
        config.getConfig().set("name", name);
        config.getConfig().set("size", 1);

        player.openInventory(inv);
    }

    public void load(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "");
        config.getConfig().set("name", name);
        config.getConfig().set("size", 1);

        player.openInventory(inv);
    }

    public void save(Inventory inv) {


        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            NBTItem nbtItem = new NBTItem(item);

            config.setString("title", name);
            config.setInteger("size", inv.getSize());

            if (item != null) {
                if (config.getConfig().getConfigurationSection("items." + i) == null) {
                    try {

                        ConfigurationSection section = config.getConfig().createSection("items." + i);
                        section.set("itemStack", nbtItem.getItem());

                        config.saveConfig();

                    } catch (NullPointerException e) {
                        ConfigurationSection newSection = config.getConfig().createSection("items.0");
                        newSection.set("time", nbtItem.getItem());

                        config.saveConfig();
                    }
                }
            }
        }
    }

    public Config getConfig() {
        return config;
    }

    public String getName() {
        return name;
    }
}
