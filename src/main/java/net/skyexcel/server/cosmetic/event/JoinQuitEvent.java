package net.skyexcel.server.cosmetic.event;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        SkyExcelNetworkCosmeticMain.armorStandManager.addPlayerArmorStand(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        SkyExcelNetworkCosmeticMain.armorStandManager.removePlayerArmorStand(e.getPlayer());
    }
}
