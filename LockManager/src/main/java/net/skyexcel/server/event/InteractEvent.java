package net.skyexcel.server.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) throws ClassNotFoundException {

        Player player = event.getPlayer();



    }
}
