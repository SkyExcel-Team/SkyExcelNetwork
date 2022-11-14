package net.skyexcel.server.skyblock.event;

import net.skyexcel.server.skyblock.data.event.SkyBlockQuickEvent;
import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();


        SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);

        if (skyBlockPlayerData.hasIsland()) {
            SkyBlock skyBlock = new SkyBlock(skyBlockPlayerData.getIsland());
            SkyBlockQuickEvent skyBlockQuickEvent = new SkyBlockQuickEvent(skyBlock.getName(), skyBlock, player);
            skyBlockQuickEvent.setCause(SkyBlockQuickEvent.QuickCuase.SERVER);
            Bukkit.getPluginManager().callEvent(skyBlockQuickEvent);
        }
    }
}
