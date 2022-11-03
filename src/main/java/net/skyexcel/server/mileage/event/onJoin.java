package net.skyexcel.server.mileage.event;

import net.skyexcel.server.data.economy.Mileage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Mileage money = new Mileage(player);
        if (money.getMoney() == -1)
            money.setMoney(0);
    }
}
