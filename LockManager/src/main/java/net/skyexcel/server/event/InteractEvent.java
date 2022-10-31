package net.skyexcel.server.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Sign;
import org.jetbrains.annotations.NotNull;

public class InteractEvent implements Listener {

    private final Material[] materials = {Material.ACACIA_SIGN, Material.BIRCH_SIGN, Material.BIRCH_WALL_SIGN,};

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) throws ClassNotFoundException {

        Player player = event.getPlayer();

    }
}
