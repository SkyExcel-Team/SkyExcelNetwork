package net.skyexcel.server.skyblock.event;

import net.skyexcel.server.skyblock.data.event.SkyBlockJoinEvent;
import net.skyexcel.server.skyblock.data.island.SkyBlock;

import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class onJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        if (playerData.hasIsland()) {
            SkyBlock islandData = new SkyBlock(playerData.getIsland());
            OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(islandData.getOwner()));
            SkyBlockJoinEvent skyBlockJoinEvent = new SkyBlockJoinEvent(islandData.getName(), islandData, player, owner);
            skyBlockJoinEvent.setJoinCause(SkyBlockJoinEvent.JoinCause.MEMBER);
            Bukkit.getPluginManager().callEvent(skyBlockJoinEvent);
        }
        event.setJoinMessage("");
    }
}
