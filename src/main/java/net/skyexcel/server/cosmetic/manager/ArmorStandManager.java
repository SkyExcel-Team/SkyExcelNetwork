package net.skyexcel.server.cosmetic.manager;

import net.skyexcel.server.cosmetic.data.ArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArmorStandManager {
    private static final Map<UUID, ArmorStand> armorstandMap = new HashMap<>();

    public boolean containsPlayer(Player player) {
        return armorstandMap.containsKey(player.getUniqueId());
    }

    public boolean containsPlayer(UUID uuid) {
        return armorstandMap.containsKey(uuid);
    }

    public ArmorStand getPlayerArmorStand(Player player) {
        return armorstandMap.get(player.getUniqueId());
    }

    public ArmorStand getPlayerArmorStand(UUID uuid) {
        return armorstandMap.get(uuid);
    }

    public void addPlayerArmorStand(Player player) {
        armorstandMap.put(player.getUniqueId(), new ArmorStand(player));
    }

    public void addPlayerArmorStand(UUID uuid) {
        armorstandMap.put(uuid, new ArmorStand(Bukkit.getPlayer(uuid)));
    }

    public void removePlayerArmorStand(Player player) {
        armorstandMap.get(player.getUniqueId()).remove();
        armorstandMap.remove(player.getUniqueId());
    }

    public void removePlayerArmorStand(UUID uuid) {
        armorstandMap.get(uuid).remove();
        armorstandMap.remove(uuid);
    }
}
