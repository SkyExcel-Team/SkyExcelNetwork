package net.skyexcel.server.items.event;

import net.skyexcel.server.items.data.Items;
import net.skyexcel.server.items.data.TestItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemsListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            TestItem testItem = new TestItem();
            System.out.println(testItem.getItemStack());
        }
    }
}
