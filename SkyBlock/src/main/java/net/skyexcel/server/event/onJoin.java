package net.skyexcel.server.event;

import net.skyexcel.server.data.island.SkyBlock;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        if (playerData.hasIsland()) {
            SkyBlock islandData = new SkyBlock(playerData.getIsland());
            islandData.onJoin(player);
        }
    }
}
