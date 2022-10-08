package net.skyexcel.server.event;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import skyexcel.data.file.Config;

public class ClickEvent implements Listener {


    private Config config;

    private Config menuConfig;

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        String title = event.getView().getTitle();
        config = new Config("menu/");
        config.setPlugin(SkyExcelNetwork.plugin);

        if (inv != null) {
            try {

                for (String file : config.fileListName()) {
                    menuConfig = new Config("menu/" + file);
                    menuConfig.setPlugin(SkyExcelNetwork.plugin);

                    if (menuConfig.getString("menu_title").equalsIgnoreCase(event.getView().getTitle())) {
                        event.setCancelled(true);
                        Menu menu = new Menu(file);

                        ConfigurationSection section;
                        for (String items : menu.getConfig().getConfig().getConfigurationSection("items.").getKeys(false)) {
                            section = menu.getConfig().getConfig().getConfigurationSection("items." + items);



                            if (section.getIntegerList("slots").contains(event.getSlot())) {

                                if (event.getClick().isLeftClick()) {

                                    menu.getLeftCommands(items).forEach(line -> {

                                        runCommand(player, line);
                                    });

                                } else if (event.getClick().isRightClick()) {
                                    menu.getRightCommands(items).forEach(line -> {

                                        runCommand(player, line);
                                    });

                                }
                            }
                        }
                    }
                }

            } catch (NullPointerException e) {

            }
        }
    }

    public static void runCommand(Player player, String line) {
        if (line.contains("[player]")) {

            player.performCommand(line.replace("[player] ", ""));
        } else if (line.contains("[op]")) {

            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), line.replace("[op] ", ""));
        } else if (line.contains("[sound]")) {
            String sound = line.replace("[sound] ", "");
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1, 1);
        }
    }
}
