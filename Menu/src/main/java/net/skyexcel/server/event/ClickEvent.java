package net.skyexcel.server.event;

import net.skyexcel.server.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
        if (inv != null) {
            try {
                Menu menu = new Menu(event.getView().getTitle());


                if (menu.getSlots(event.getSlot()).contains(event.getSlot())) {

                    if (event.getClick().isLeftClick()) {
                        menu.getLeftCommands(event.getSlot()).forEach(line -> {
                            runCommand(player, line);
                        });
                    } else if (event.getClick().isRightClick()) {
                        menu.getRightCommands(event.getSlot()).forEach(line -> {
                            runCommand(player, line);
                        });
                    }
                    event.setCancelled(true);
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
