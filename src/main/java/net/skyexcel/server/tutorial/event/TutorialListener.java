package net.skyexcel.server.tutorial.event;

import net.skyexcel.server.warp.data.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TutorialListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Warp warp = new Warp("tutorial");

        if (!player.hasPlayedBefore()) {
            event.setJoinMessage(player.getDisplayName() + " 님이 처음으로 서버에 입장 하였습니다!");
            player.teleport(warp.getLocation());
        }
    }
}
