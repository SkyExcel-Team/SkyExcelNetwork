package net.skyexcel.server.menu;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.event.ClickEvent;
import net.skyexcel.server.util.Item;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;
import skyexcel.data.file.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu {

    private String name;
    private int size;

    private Config config;

    private String title;

    private ConfigurationSection section;

    private Inventory inv;

    public Menu(String name) {
        this.name = name;
        config = new Config("menu/" + name);
        this.title = name;
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void create() {
        Configuration defualt = SkyExcelNetwork.defaultConfig.loadDefualtConfig();

        config.getConfig().setDefaults(defualt);
        config.getConfig().options().copyDefaults(true);
        config.saveConfig();
    }

    public void reload() {
        config.reloadConfig();
    }

    public void load(Player player) {
        size = config.getConfig().getInt("size") * 9;
        title = config.getString("menu_title");

        inv = Bukkit.createInventory(null, size, title);

        config.getConfig().getStringList("open_settings").forEach(line ->{
            ClickEvent.runCommand(player,line);
        });


        for (String items : config.getConfig().getConfigurationSection("items.").getKeys(false)) {

            section = config.getConfig().getConfigurationSection("items." + items);

            Material material = Material.valueOf(section.getString("material"));


            if (material.name() != "PLAYER_HEAD") {


                @NotNull List<Integer> slot = section.getIntegerList("slots");

                int amount = section.getInt("amount");
                ItemStack item = new ItemStack(material, amount);


                if (section.get("custommodeldata") != null) {
                    int modeldata = section.getInt("custommodeldata");
                    item.setCustomModelData(modeldata);
                }

                List<String> lore = section.getStringList("lore");
                String name = section.getString("display_name");

                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName(name);
                meta.setLore(Translate.translate(lore));

                item.setItemMeta(meta);

                slot.forEach(slots -> {
                    inv.setItem(slots, item);
                });
            } else {
                @NotNull List<Integer> slot = section.getIntegerList("slots");

                List<String> lore = section.getStringList("lore");
                String name = section.getString("display_name");


                if (section.get("custommodeldata") != null) {
                    int modeldata = section.getInt("custommodeldata");

                    ItemStack item = Item.playerSkull(player.getDisplayName(), name, lore);
                    ItemMeta meta = item.getItemMeta();

                    meta.setCustomModelData(modeldata);
                    item.setItemMeta(meta);
                    slot.forEach(slots -> {
                        inv.setItem(slots, item);
                    });
                } else {
                    ItemStack item = Item.playerSkull(player.getDisplayName(), name, lore);

                    slot.forEach(slots -> {
                        inv.setItem(slots, item);
                    });
                }
            }
        }
        player.openInventory(inv);
    }

    public ItemStack getItemStack(int slot) {
        section = config.getConfig().getConfigurationSection("items." + slot);

        Material material = Material.valueOf(section.getString("material"));
        int amount = section.getInt("amount");

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = section.getStringList("lore");
        String name = section.getString("display_name");
        meta.setLore(lore);
        meta.setDisplayName(name);

        item.setItemMeta(meta);

        return item;
    }

    public List<Integer> getSlots(int slot) {
        section = config.getConfig().getConfigurationSection("items." + slot);
        if (section.get("slots") != null)
            return section.getIntegerList("slots");

        return null;
    }

    public List<String> getLeftCommands(String slot) {
        section = config.getConfig().getConfigurationSection("items." + slot);

        return section.getStringList("left_click_commands");
    }

    public List<String> getRightCommands(String slot) {
        section = config.getConfig().getConfigurationSection("items." + slot);

        return section.getStringList("right_click_commands");
    }


    public String getTitle() {
        return title;
    }

    public Config getConfig() {
        return config;
    }
}
