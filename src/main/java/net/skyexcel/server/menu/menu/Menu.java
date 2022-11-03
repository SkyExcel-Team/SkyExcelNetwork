package net.skyexcel.server.menu.menu;

import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.menu.event.ClickEvent;
import net.skyexcel.server.menu.util.Item;
import net.skyexcel.server.menu.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import skyexcel.data.file.Config;

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
        config = new Config("Menu-menu/" + name);
        this.title = name;
        config.setPlugin(SkyExcelNetworkMenuMain.plugin);
    }

    public void create() {
        Configuration defualt = SkyExcelNetworkMenuMain.defaultConfig.loadDefualtConfig();

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

        config.getConfig().getStringList("open_settings").forEach(line -> {
            ClickEvent.runCommand(player, line);
        });


        for (String items : Objects.requireNonNull(config.getConfig().getConfigurationSection("items.")).getKeys(false)) {

            section = config.getConfig().getConfigurationSection("items." + items);

            assert section != null;
            Material material = Material.valueOf(section.getString("material"));


            if (material.name() != "PLAYER_HEAD") {


                @NotNull List<Integer> slot = section.getIntegerList("slots");

                int amount = section.getInt("amount");
                ItemStack item = new ItemStack(material, amount);


                if (section.get("custommodeldata") != null) {
                    int modeldata = section.getInt("custommodeldata");
                    item.getItemMeta().setCustomModelData(modeldata);
                }

                List<String> lore = section.getStringList("lore");
                String name = section.getString("display_name");

                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                meta.setLore(Translate.translate(lore, player));

                item.setItemMeta(meta);

                slot.forEach(slots -> {
                    inv.setItem(slots, item);
                });
            } else {
                if (section.get("hdb") != null) {
                    @NotNull List<Integer> slot = section.getIntegerList("slots");
                    int texture = section.getInt("hdb");
                    int amount = section.getInt("amount");

                    assert SkyExcelNetworkMenuMain.hdb != null;

                    ItemStack item = SkyExcelNetworkMenuMain.hdb.getItemHead(String.valueOf(texture));

                    item.setAmount(amount);
                    List<String> lore = section.getStringList("lore");

                    String name = section.getString("display_name");
                    ItemMeta meta = item.getItemMeta();


                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    meta.setLore(Translate.translate(lore, player));

                    if (section.get("custommodeldata") != null) {

                        int modeldata = section.getInt("custommodeldata");
                        meta.setCustomModelData(modeldata);

                        item.setItemMeta(meta);

                        slot.forEach(slots -> {
                            inv.setItem(slots, item);
                        });

                    } else {
                        item.setItemMeta(meta);
                        slot.forEach(slots -> {
                            inv.setItem(slots, item);
                        });
                    }


                } else {
                    @NotNull List<Integer> slot = section.getIntegerList("slots");
                    int amount = section.getInt("amount");
                    List<String> lore = section.getStringList("lore");
                    String name = section.getString("display_name");


                    if (section.get("custommodeldata") != null) {
                        int modeldata = section.getInt("custommodeldata");

                        ItemStack item = Item.playerSkull(player.getDisplayName(), name, lore);
                        ItemMeta meta = item.getItemMeta();
                        item.setAmount(amount);
                        meta.setCustomModelData(modeldata);
                        item.setItemMeta(meta);
                        slot.forEach(slots -> {
                            inv.setItem(slots, item);
                        });
                    } else {
                        ItemStack item = Item.playerSkull(player.getDisplayName(), ChatColor.translateAlternateColorCodes('&', name), lore);

                        slot.forEach(slots -> {
                            inv.setItem(slots, item);
                        });
                    }
                }
            }
        }
        player.openInventory(inv);
    }


    public List<Integer> getSlots(String slot) {
        section = config.getConfig().getConfigurationSection("items." + slot);

        return section.getIntegerList("slots");
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
