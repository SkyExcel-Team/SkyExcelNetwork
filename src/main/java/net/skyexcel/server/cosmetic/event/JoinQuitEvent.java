package net.skyexcel.server.cosmetic.event;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain.armorstandManager;

public class JoinQuitEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        armorstandManager.addPlayerArmorStand(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (armorstandManager.containsPlayer(player))
            SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(player);
    }
}
