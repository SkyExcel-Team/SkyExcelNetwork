package net.skyexcel.server.cosmetic.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain.armorstandManager;

public class PlayerListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR) return;

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);

        if (true) //TODO - 등 치장 있는지 확인
            armorstandManager.getPlayerArmorStand(player).teleport();
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR) return;

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);

        if (true) //TODO - 등 치장 있는지 확인
            armorstandManager.getPlayerArmorStand(player).teleport();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();

        if (armorstandManager.containsPlayer(player))
            armorstandManager.removePlayerArmorStand(player);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR) return;

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);
    }

    @EventHandler
    public void onChangeGameMode(PlayerGameModeChangeEvent e) {
        Player player = e.getPlayer();

        if (e.getNewGameMode() == GameMode.SPECTATOR) return;

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);
    }
}
