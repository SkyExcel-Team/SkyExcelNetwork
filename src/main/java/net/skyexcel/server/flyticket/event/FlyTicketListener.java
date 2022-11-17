package net.skyexcel.server.flyticket.event;

import net.skyexcel.server.flyticket.data.FlyTime;
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
        player.sendMessage("날아다님 시간 줄음 ㅅㄱ");

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        FlyTime flyTime = new FlyTime(player);

        if (player.isFlying()) {
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
