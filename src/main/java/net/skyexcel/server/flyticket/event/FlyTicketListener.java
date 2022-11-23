package net.skyexcel.server.flyticket.event;

import net.skyexcel.server.flyticket.data.FlyTime;
import net.skyexcel.server.flyticket.events.PlayerFlyingEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import skyexcel.util.ActionBar;

public class FlyTicketListener implements org.bukkit.event.Listener {


    @EventHandler
    public void on(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();


    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();


        if (player.isFlying()) {
            PlayerFlyingEvent playerFlyingEvent = new PlayerFlyingEvent(player, PlayerFlyingEvent.Flying.FLYING);
            Bukkit.getPluginManager().callEvent(playerFlyingEvent);
        } else {
            PlayerFlyingEvent playerFlyingEvent = new PlayerFlyingEvent(player, PlayerFlyingEvent.Flying.GROUND);
            Bukkit.getPluginManager().callEvent(playerFlyingEvent);
        }
    }


    @EventHandler
    public void onFlying(PlayerFlyingEvent event) {
        Player player = event.getPlayer();
        if (PlayerFlyingEvent.Flying.FLYING.equals(event.getFlying())) {
            ActionBar.sendMessage(player, " 123년 1달 1일 1시간 1분 1초 남았습니다");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FlyTime flyTime = new FlyTime(player);
        flyTime.setDefault();
    }
}
