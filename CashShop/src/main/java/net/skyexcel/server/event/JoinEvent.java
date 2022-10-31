package net.skyexcel.server.event;

import net.skyexcel.server.command.CashCmd;
import net.skyexcel.server.data.Cash;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            Cash cash = new Cash(player);
            cash.Set(0);
        }

    }
}
