package net.skyexcel.server.menu.event;

import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.menu.menu.Menu;
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
        config = new Config("Menu-menu/");
        config.setPlugin(SkyExcelNetworkMenuMain.plugin);

        if (inv != null) {
            try {
                for (String file : config.fileListName()) {
                    menuConfig = new Config("Menu-menu/" + file);
                    menuConfig.setPlugin(SkyExcelNetworkMenuMain.plugin);

                    if (menuConfig.getString("menu_title").equalsIgnoreCase(event.getView().getTitle())) {
                        event.setCancelled(true);
                        Menu menu = new Menu(file);

                        ConfigurationSection section;
                        for (String items : menu.getConfig().getConfig().getConfigurationSection("items.").getKeys(false)) {

                            section = menu.getConfig().getConfig().getConfigurationSection("items." + items);
                            if (menu.getSlots(items).contains(event.getSlot())) {

                                if (event.getClick().isLeftClick()) {

                                    menu.getLeftCommands(items).forEach(line -> {
                                        runCommand(player, line);
                                    });

                                } else if (event.getClick().isRightClick()) {
                                    menu.getRightCommands(items).forEach(line -> {
                                        runCommand(player, line);
                                    });
                                }
                                break; //누른 슬롯이 아이템 슬롯중에 있으면 break를 통해 for문을 탈출하여 다른 아이템 커맨드까지 불러오는 현상을 방지한다.
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
        } else if (line.contains("[close]")) {
            player.closeInventory();
        }
    }
}
