package net.skyexcel.server.cosmetic.event;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (!SkyExcelNetworkCosmeticMain.armorStandManager.containsPlayer(player))
            SkyExcelNetworkCosmeticMain.armorStandManager.addPlayerArmorStand(player);

        if (true) //TODO - 등 치장 있는지 확인
            SkyExcelNetworkCosmeticMain.armorStandManager.getPlayerArmorStand(player).teleport();
    }
}
