package net.skyexcel.server.cosmetic.data;

import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
            SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(owner);
            return;
        }

        if (!armorstand.isValid()) {
            spawnArmorStand();
        }

        else if (!(owner.isSleeping() || owner.getGameMode() == GameMode.SPECTATOR) && !owner.getPassengers().contains(armorstand)) {
            owner.addPassenger(armorstand);
        }

        reloadHelmet();

        if (owner.isSwimming()) {
            armorstand.setRotation(owner.getLocation().getYaw(), 180);
        } else if ((owner.isSleeping() || owner.getGameMode() == GameMode.SPECTATOR) && owner.getPassengers().contains(armorstand)) {
            SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(owner);
        } else {
            armorstand.setRotation(owner.getLocation().getYaw(), owner.getLocation().getPitch());
        }

    }

    public void remove() {
        armorstand.remove();
    }

    public void reloadHelmet() {
        if (armorstand != null) {
            if (getBackCosmetic() == null) {
                SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(owner);
                return;
            }
            if (getBackCosmetic().getType() == Material.AIR) {
                SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(owner);
                return;
            }

            ItemStack item = new ItemStack(getBackCosmetic().getType(), 1); //TODO
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(getBackCosmetic().getCustomModelData()); //TODO
            item.setItemMeta(meta);

            armorstand.getEquipment().setHelmet(item);
        }
    }

    private void spawnArmorStand() {
        if (armorstand != null)
            remove();
        if (getBackCosmetic() == null) {
            SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(owner);
            return;
        }
        if (getBackCosmetic().getType() == Material.AIR) {
            SkyExcelNetworkCosmeticMain.armorstandManager.removePlayerArmorStand(owner);
            return;
        }

        armorstand = (org.bukkit.entity.ArmorStand) owner.getWorld().spawnEntity(owner.getLocation(), EntityType.ARMOR_STAND);

        ItemStack item = new ItemStack(getBackCosmetic().getType(), 1); //TODO
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(getBackCosmetic().getCustomModelData()); //TODO
        item.setItemMeta(meta);

        armorstand.getEquipment().setHelmet(item);
        armorstand.setBasePlate(false);
        armorstand.setGravity(false);
        armorstand.setVisible(false);
        armorstand.setMarker(true);
    }

    private Cosmetic.BACK getBackCosmetic() {
        Cosmetic.BACK backCosmetic = new PlayerCosmeticData(owner).getWearBackCosmetic();

        if (backCosmetic != null)
            return backCosmetic;
        else {
            try {
                remove();
            } catch(Exception ignored) {}
            return null;
        }
    }
}
