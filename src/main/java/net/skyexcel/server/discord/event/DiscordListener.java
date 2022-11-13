package net.skyexcel.server.discord.event;

import net.skyexcel.server.discord.SkyExcelNetworkDiscordMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class DiscordListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!SkyExcelNetworkDiscordMain.data.getConfig().contains(player.getUniqueId().toString())) {
            SkyExcelNetworkDiscordMain.data.setString(player.getUniqueId().toString(), "");
        }
    }
}