package net.skyexcel.server.cosmetic.event;

import net.skyexcel.server.cosmetic.data.ArmorStand;
import net.skyexcel.server.cosmetic.data.Cosmetic;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import static net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain.armorstandManager;

public class EntityListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR) return;
        PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
        if (playerCosmeticData.getWearBackCosmetic() == Cosmetic.BACK.NONE) {
            armorstandManager.removePlayerArmorStand(player);
            return;
        }

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);

        if (playerCosmeticData.getWearBackCosmetic() != Cosmetic.BACK.NONE)
            armorstandManager.getPlayerArmorStand(player).teleport();
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR) return;
        PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
        if (playerCosmeticData.getWearBackCosmetic() == Cosmetic.BACK.NONE) {
            armorstandManager.removePlayerArmorStand(player);
            return;
        }

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);

        if (playerCosmeticData.getWearBackCosmetic() != Cosmetic.BACK.NONE)
            armorstandManager.getPlayerArmorStand(player).teleport();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();

        if (player.getGameMode() == GameMode.SPECTATOR) return;

        if (armorstandManager.containsPlayer(player))
            armorstandManager.removePlayerArmorStand(player);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SPECTATOR) return;

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);

        PlayerCosmeticData playerCosmeticData = new PlayerCosmeticData(player);
        playerCosmeticData.refreshBack();
        playerCosmeticData.refreshHat();
        playerCosmeticData.refreshOffhand();
    }

    @EventHandler
    public void onChangeGameMode(PlayerGameModeChangeEvent e) {
        Player player = e.getPlayer();

        if (e.getNewGameMode() == GameMode.SPECTATOR) {
            armorstandManager.removePlayerArmorStand(player);
            return;
        }

        if (!armorstandManager.containsPlayer(player))
            armorstandManager.addPlayerArmorStand(player);
    }

    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        Entity entity = e.getEntity();

        if (!(entity instanceof org.bukkit.entity.ArmorStand)) return;
        if (((Player) e.getDismounted()).getGameMode() == GameMode.SPECTATOR) return;

        for (ArmorStand armorStand : armorstandManager.getArmorStands()) {
            if (armorStand.getArmorStandEntity() == entity) {
                if (!armorStand.getOwner().getPassengers().contains(entity))
                    armorStand.getOwner().addPassenger(entity);
            }
        }
    }
}
