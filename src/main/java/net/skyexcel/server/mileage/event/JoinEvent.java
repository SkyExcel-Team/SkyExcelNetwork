package net.skyexcel.server.mileage.event;

import net.skyexcel.server.mileage.data.Mileage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Mileage mileage = new Mileage(player);
        if (mileage.getLong() == -1) {
            mileage.setAmount(0);
        }

    }


}
