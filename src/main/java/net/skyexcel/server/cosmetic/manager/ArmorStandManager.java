package net.skyexcel.server.cosmetic.manager;

import net.skyexcel.server.cosmetic.data.ArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ArmorStandManager {
    private static final Map<UUID, ArmorStand> armorstandMap = new HashMap<>();
    private static final Map<UUID, org.bukkit.entity.ArmorStand> entityMap = new HashMap<>();

    public boolean containsPlayer(Player player) {
        return armorstandMap.containsKey(player.getUniqueId());
    }

    public boolean containsPlayer(UUID uuid) {
        return armorstandMap.containsKey(uuid);
    }

    public boolean containsArmorStandEntity(org.bukkit.entity.ArmorStand armorstand) {
        return entityMap.containsValue(armorstand);
    }

    public List<org.bukkit.entity.ArmorStand> getArmorStandEntities() {
        return entityMap.values().stream().toList();
    }

    public List<ArmorStand> getArmorStands() {
        return armorstandMap.values().stream().toList();
    }

    public ArmorStand getPlayerArmorStand(Player player) {
        return armorstandMap.get(player.getUniqueId());
    }

    public ArmorStand getPlayerArmorStand(UUID uuid) {
        return armorstandMap.get(uuid);
    }

    public org.bukkit.entity.ArmorStand getPlayerArmorStandEntity(Player player) {
        return entityMap.get(player.getUniqueId());
    }

    public org.bukkit.entity.ArmorStand getPlayerArmorStandEntity(UUID uuid) {
        return entityMap.get(uuid);
    }

    public void addPlayerArmorStand(Player player) {
        armorstandMap.put(player.getUniqueId(), new ArmorStand(player));
        entityMap.put(player.getUniqueId(), armorstandMap.get(player.getUniqueId()).getArmorStandEntity());
    }

    public void addPlayerArmorStand(UUID uuid) {
        armorstandMap.put(uuid, new ArmorStand(Bukkit.getPlayer(uuid)));
        entityMap.put(uuid, armorstandMap.get(uuid).getArmorStandEntity());
    }

    public void removePlayerArmorStand(Player player) {
        armorstandMap.get(player.getUniqueId()).remove();
        armorstandMap.remove(player.getUniqueId());
        entityMap.remove(player.getUniqueId());
    }

    public void removePlayerArmorStand(UUID uuid) {
        armorstandMap.get(uuid).remove();
        armorstandMap.remove(uuid);
        entityMap.remove(uuid);
    }
}
