package net.skyexcel.server.cosmetic.data;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ArmorStand {
    private final Player owner;
    private final Entity armorstand;

    public ArmorStand(Player owner) {
        this.owner = owner;

        armorstand = owner.getWorld().spawnEntity(owner.getLocation(), EntityType.ARMOR_STAND);
        armorstand.setGravity(false);
    }

    public Player getOwner() {
        return owner;
    }

    public Entity getEntity() {
        return armorstand;
    }

    public void teleport(Location loc) {
        armorstand.teleport(loc);
    }

    public void remove() {
        armorstand.remove();
    }
}
