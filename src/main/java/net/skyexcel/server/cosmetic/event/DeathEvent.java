package net.skyexcel.server.cosmetic.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().removeIf(drop -> List.of(Material.COPPER_INGOT, Material.IRON_INGOT, Material.GOLD_INGOT).contains(drop.getType()) && drop.getItemMeta().hasCustomModelData());
    }
}
