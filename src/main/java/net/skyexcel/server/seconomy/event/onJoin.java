package net.skyexcel.server.seconomy.event;

import net.skyexcel.server.data.economy.SEconomy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SEconomy money = new SEconomy(player);
        if (money.getMoney() == -1)
            money.setMoney(0);
    }
}
