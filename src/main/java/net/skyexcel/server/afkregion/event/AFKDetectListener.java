package net.skyexcel.server.afkregion.event;

import net.skyexcel.server.afkregion.data.AFKDetectData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AFKDetectListener implements Listener {


    private Map<UUID, AFKDetectData> detectDataHashMap = new HashMap<>();


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        int fromX = (int) event.getFrom().getX();
        int fromZ = (int) event.getFrom().getZ();
        int fromY = (int) event.getFrom().getY();
        int toX = (int) event.getTo().getX();
        int toZ = (int) event.getTo().getZ();
        int toY = (int) event.getTo().getY();

        if (fromX == toX && fromZ == toZ && fromY == toY) return;

        Player player = event.getPlayer();
        if (detectDataHashMap.containsKey(player.getUniqueId())) {
            AFKDetectData afkDetectData = detectDataHashMap.get(player.getUniqueId());
            afkDetectData.setLastMoved(System.currentTimeMillis());

            if (detectDataHashMap.containsKey(player.getUniqueId())) {

                detectDataHashMap.put(player.getUniqueId(), afkDetectData);
                player.sendMessage("움직임");
            }
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (detectDataHashMap.containsKey(player.getUniqueId())) {
            AFKDetectData afkDetectData = detectDataHashMap.get(player.getUniqueId());
        }
    }

    @EventHandler
    public void playerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player player) {

        }
    }
}
