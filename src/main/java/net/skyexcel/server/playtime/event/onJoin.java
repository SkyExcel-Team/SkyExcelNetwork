package net.skyexcel.server.playtime.event;

import net.skyexcel.server.data.PlayTime;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayTime playTime = new PlayTime(player);
        playTime.saveDefault();

    }
}
