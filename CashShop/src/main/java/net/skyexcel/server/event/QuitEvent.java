package net.skyexcel.server.event;

import net.skyexcel.server.command.CashCmd;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        new CashCmd();
    }
}
