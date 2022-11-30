package net.skyexcel.server.essentials.playtime.event;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.essentials.playtime.data.PlayTime;
import net.skyexcel.server.essentials.playtime.scheduler.PlayTimeScheduler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayTimeListener implements Listener {

    private HashMap<UUID, PlayTimeScheduler> playTimeHashMap = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PlayTime playTime = new PlayTime(player);
        if (!playTimeHashMap.containsKey(player.getUniqueId())) {
            playTime.saveDefault();

            PlayTimeScheduler scheduler = new PlayTimeScheduler(player, 20 * 300);
            scheduler.runTaskTimer(SkyExcelNetworkMain.getPlugin(), 120, 20 * 300); //300초 후 저장
            playTimeHashMap.put(player.getUniqueId(), scheduler);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (playTimeHashMap.containsKey(player.getUniqueId())) {
            PlayTimeScheduler scheduler = playTimeHashMap.get(player.getUniqueId());
            scheduler.cancel();
            playTimeHashMap.remove(player.getUniqueId());
        }
    }
}
