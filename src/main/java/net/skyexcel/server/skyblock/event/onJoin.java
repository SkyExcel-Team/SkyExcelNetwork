package net.skyexcel.server.skyblock.event;

import net.skyexcel.server.skyblock.data.island.SkyBlock;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class onJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SkyBlockPlayerData playerData = new SkyBlockPlayerData(player);
        if (playerData.hasIsland()) {
            SkyBlock islandData = new SkyBlock(playerData.getIsland());

            List<String> members = islandData.getMembers();

            if (members.contains(player.getUniqueId().toString()) || islandData.getOwner().equalsIgnoreCase(player.getUniqueId().toString())) {
                for (String member : members) {
                    Player online = Bukkit.getPlayer(member);
                    online.getPlayer().sendMessage(player.getPlayer().getDisplayName() + " 님이 입장 하였습니다!");
                }
            }
        }
    }
}
