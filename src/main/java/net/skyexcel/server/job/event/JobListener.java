package net.skyexcel.server.job.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class JobListener implements Listener {

    @EventHandler
    public void test(PlayerArmorStandManipulateEvent event) {
        Player player = event.getPlayer();
        
    }
}
