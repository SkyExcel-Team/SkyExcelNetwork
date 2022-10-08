package net.skyexcel.server.event;

import net.skyexcel.server.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickEvent implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        String title = event.getView().getTitle();
        if (inv != null) {
            try {
                Menu menu = new Menu(event.getView().getTitle());
                if (menu.getTitle() == title) {
                    event.setCancelled(true);
                    ConfigurationSection section;
                    for (String items : menu.getConfig().getConfig().getConfigurationSection("items.").getKeys(false)) {
                        section = menu.getConfig().getConfig().getConfigurationSection("items." + items);

                        if (section.getIntegerList("slots").contains(event.getSlot())) {

                            if (event.getClick().isLeftClick()) {
                                section.getIntegerList("slots").forEach(slot->{
                                    menu.getLeftCommands(slot).forEach(line -> {
                                        runCommand(player, line);
                                    });
                                });

                            } else if (event.getClick().isRightClick()) {
                                section.getIntegerList("slots").forEach(slot->{
                                    menu.getRightCommands(slot).forEach(line -> {
                                        runCommand(player, line);
                                    });
                                });
                            }
                        }
                    }
                }
            } catch (NullPointerException e) {

            }
        }
    }

    public void runCommand(Player player, String line) {
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
