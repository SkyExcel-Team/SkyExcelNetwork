package net.skyexcel.server.cosmetic.data;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain.armorstandManager;

public class ArmorStand {
    private final Player owner;
    private final PlayerCosmeticData data;

    private org.bukkit.entity.ArmorStand armorstand;

    public ArmorStand(Player owner) {
        this.owner = owner;
        this.data = new PlayerCosmeticData(owner);

        spawnArmorStand();
        if (armorstand != null)
            owner.addPassenger(armorstand);
    }

    public Player getOwner() {
        return owner;
    }

    public org.bukkit.entity.ArmorStand getArmorStandEntity() {
        return armorstand;
    }

    public void teleport() {
        if (armorstand == null) {
            armorstandManager.removePlayerArmorStand(owner);
            return;
        }

        if (!armorstand.isValid()) {
            spawnArmorStand();
        } else if (!(owner.isSleeping() || owner.getGameMode() == GameMode.SPECTATOR) && !owner.getPassengers().contains(armorstand)) {
            owner.addPassenger(armorstand);
        }

        if (owner.isSwimming()) {
            armorstand.setRotation(owner.getLocation().getYaw(), 180);
        } else if ((owner.isSleeping() || owner.getGameMode() == GameMode.SPECTATOR) && owner.getPassengers().contains(armorstand)) {
            armorstandManager.removePlayerArmorStand(owner);
        } else {
            armorstand.setRotation(owner.getLocation().getYaw(), owner.getLocation().getPitch());
        }

    }

    public void remove() {
        armorstand.remove();
    }

    public void reloadHelmet() {
        if (armorstand != null) {
            Cosmetic.BACK cosmetic = getBackCosmetic();

            if (cosmetic == Cosmetic.BACK.NONE || cosmetic == null) {
                armorstandManager.removePlayerArmorStand(owner);
                return;
            }

            if (!armorstandManager.containsPlayer(owner)) {
                armorstandManager.addPlayerArmorStand(owner);
                armorstandManager.getPlayerArmorStand(owner).reloadHelmet();
                return;
            }

            ItemStack item = new ItemStack(getBackCosmetic().getType(), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(getBackCosmetic().getCustomModelData());
            meta.setDisplayName(getBackCosmetic().getName());
            item.setItemMeta(meta);

            armorstand.getEquipment().setHelmet(item);
        } else
            armorstandManager.removePlayerArmorStand(owner);
    }

    private void spawnArmorStand() {
        if (armorstand != null)
            remove();
        if (getBackCosmetic() == null) {
            armorstandManager.removePlayerArmorStand(owner);
            return;
        }
        if (getBackCosmetic().getType() == Material.AIR) {
            armorstandManager.removePlayerArmorStand(owner);
            return;
        }

        armorstand = (org.bukkit.entity.ArmorStand) owner.getWorld().spawnEntity(owner.getLocation(), EntityType.ARMOR_STAND);
        armorstand.setBasePlate(false);
        armorstand.setGravity(false);
        armorstand.setVisible(false);
        armorstand.setMarker(true);

        ItemStack item = new ItemStack(getBackCosmetic().getType(), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(getBackCosmetic().getCustomModelData());
        meta.setDisplayName(getBackCosmetic().getName());
        item.setItemMeta(meta);

        armorstand.getEquipment().setHelmet(item);
    }

    private Cosmetic.BACK getBackCosmetic() {
        Cosmetic.BACK backCosmetic = new PlayerCosmeticData(owner).getWearBackCosmetic();

        if (backCosmetic != null)
            return backCosmetic;
        else {
            try {
                remove();
            } catch (Exception ignored) {
            }
            return null;
        }
    }
}
