package net.skyexcel.server.cosmetic.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorStand {
    private final Player owner;
    private Entity armorstand;

    public ArmorStand(Player owner) {
        this.owner = owner;

        armorstand = owner.getWorld().spawnEntity(owner.getLocation().add(0, 1.4, 0), EntityType.ARMOR_STAND);
        ItemStack var = new ItemStack(Material.GOLD_INGOT, 1); //TODO
        ItemMeta var1 = var.getItemMeta();
        var1.setCustomModelData(4); //TODO
        var.setItemMeta(var1);

        ((org.bukkit.entity.ArmorStand) armorstand).getEquipment().setHelmet(var);
        ((org.bukkit.entity.ArmorStand) armorstand).setVisible(false);
        armorstand.setGravity(false);

        owner.addPassenger(armorstand);
    }

    public Player getOwner() {
        return owner;
    }

    public Entity getEntity() {
        return armorstand;
    }

    public void teleport() {
        armorstand.setRotation(owner.getLocation().getYaw(), owner.getLocation().getPitch());
    }

    public void remove() {
        armorstand.remove();
    }
}
