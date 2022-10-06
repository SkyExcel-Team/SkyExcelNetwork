package net.skyexcel.server.menu;

import net.skyexcel.server.SkyExcelNetwork;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
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

    private List<ItemStack> items = new ArrayList<>();

    private Inventory inv;

    public Menu(String name) {
        this.name = name;
        config = new Config("menu/" + name);
        config.setPlugin(SkyExcelNetwork.plugin);
    }

    public void create() {
        config.getConfig().setDefaults(Objects.requireNonNull(SkyExcelNetwork.defaultConfig.getConfig().getDefaults()));
        config.getConfig().options().copyDefaults(true);
        config.saveConfig();
    }

    public void reload() {
        config.reloadConfig();

    }

    public void load(Player player) {


        size = config.getInteger("size") * 9;
        title = config.getString("menu_title");

        inv = Bukkit.createInventory(null, size, title);

        for (String items : config.getConfig().getConfigurationSection("items.").getKeys(false)) {

            section = config.getConfig().getConfigurationSection("items." + items);

            Material material = Material.valueOf(section.getString("material"));
            @NotNull List<Integer> slot = section.getIntegerList("slot");
            int amount = section.getInt("amount");
            int modeldata = section.getInt("custommodeldata");

            List<String> lore = section.getStringList("lore");
            String name = section.getString("display_name");
            ItemStack item = new ItemStack(material, amount);

            item.setCustomModelData(modeldata);

            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);

            slot.forEach(slots -> {
                this.inv.setItem(slots, item);
            });
        }
        player.openInventory(inv);
    }
}
