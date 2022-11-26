package net.skyexcel.server.cosmetic.scheduler;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import net.skyexcel.server.cosmetic.data.ArmorStand;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class ArmorStandMoveListenScheduler extends BukkitRunnable {
    private final Map<ArmorStand, Location> armorStandLocationMap = new HashMap<>();

    @Override
    public void run() {
        SkyExcelNetworkCosmeticMain.armorstandManager.getArmorStands().forEach(armorstand -> {
            if (armorstand.getArmorStandEntity() == null) {
                armorStandLocationMap.remove(armorstand);
                return;
            }

            if (!armorStandLocationMap.containsKey(armorstand))
                armorStandLocationMap.put(armorstand, armorstand.getArmorStandEntity().getLocation());

            if (armorStandLocationMap.get(armorstand) != armorstand.getArmorStandEntity().getLocation())
                armorstand.teleport();
        });
    }
}
