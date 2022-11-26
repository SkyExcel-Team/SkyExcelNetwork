package net.skyexcel.server.cosmetic.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.cosmetic.SkyExcelNetworkCosmeticMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skyexcel.data.file.Config;

import java.util.*;

public class PlayerCosmeticData {
    private final Player player;
    private Config data = null;

    private final List<Cosmetic.BACK> BACK = new ArrayList<>();
    private final List<Cosmetic.HAT> HAT = new ArrayList<>();
    private final List<Cosmetic.OFFHAND> OFFHAND = new ArrayList<>();

    public PlayerCosmeticData(Player player) {
        this.player = player;

        loadConfig();
    }

    public List<Cosmetic.BACK> getBackCosmetics() { reloadConfig(); return BACK; }
    public List<Cosmetic.HAT> getHatCosmetics() { reloadConfig(); return HAT; }
    public List<Cosmetic.OFFHAND> getOffhandCosmetics() { reloadConfig(); return OFFHAND; }

    public void addCosmetic(Cosmetic.BACK cosmetic) { reloadConfig(); BACK.add(cosmetic); saveConfig(); }
    public void addCosmetic(Cosmetic.HAT cosmetic) { reloadConfig(); HAT.add(cosmetic); saveConfig(); }
    public void addCosmetic(Cosmetic.OFFHAND cosmetic) { reloadConfig(); OFFHAND.add(cosmetic); saveConfig(); }

    public void removeCosmetic(Cosmetic.BACK cosmetic) { reloadConfig(); BACK.remove(cosmetic); saveConfig(); }
    public void removeCosmetic(Cosmetic.HAT cosmetic) { reloadConfig(); HAT.remove(cosmetic); saveConfig(); }
    public void removeCosmetic(Cosmetic.OFFHAND cosmetic) { reloadConfig(); OFFHAND.remove(cosmetic); saveConfig(); }

    public void setWearBackCosmetic(Cosmetic.BACK cosmetic) { reloadConfig(); data.setString("cosmetics.wear.back", cosmetic.name()); refreshBack(); }
    public void setWearHatCosmetic(Cosmetic.HAT cosmetic) { reloadConfig(); data.setString("cosmetics.wear.hat", cosmetic.name()); refreshBack(); }
    public void setWearOffhandCosmetic(Cosmetic.OFFHAND cosmetic) { reloadConfig(); data.setString("cosmetics.wear.offhand", cosmetic.name()); refreshBack(); }

    public Cosmetic.BACK getWearBackCosmetic() { reloadConfig(); return Cosmetic.BACK.valueOf(data.getString("cosmetics.wear.back")); }
    public Cosmetic.HAT getWearHatCosmetic() { reloadConfig(); return Cosmetic.HAT.valueOf(data.getString("cosmetics.wear.hat")); }
    public Cosmetic.OFFHAND getWearOffhandCosmetic() { reloadConfig(); return Cosmetic.OFFHAND.valueOf(data.getString("cosmetics.wear.offhand")); }

    private void reloadConfig() {
        BACK.clear();
        HAT.clear();
        OFFHAND.clear();

        loadConfig();
    }

    private void loadConfig() {
        data = new Config("data/" + this.player.getUniqueId());
        data.setPlugin(SkyExcelNetworkMain.getPlugin());

        if (data.getConfig().getConfigurationSection("cosmetics") == null) {
            data.setString("cosmetics.wear.back", "NONE");
            data.setString("cosmetics.wear.hat", "NONE");
            data.setString("cosmetics.wear.offhand", "NONE");

            data.setList("cosmetics.own.back", List.of("NONE"));
            data.setList("cosmetics.own.hat", List.of("NONE"));
            data.setList("cosmetics.own.offhand", List.of("NONE"));
        }

        if (data.getConfig().getList("cosmetics.own.back") != null)
            data.getConfig().getList("cosmetics.own.back").forEach(key -> {
                BACK.add(Cosmetic.BACK.valueOf((String) key));
            });
        if (data.getConfig().getList("cosmetics.own.hat") != null)
            data.getConfig().getList("cosmetics.own.hat").forEach(key -> {
                HAT.add(Cosmetic.HAT.valueOf((String) key));
            });
        if (data.getConfig().getList("cosmetics.own.offhand") != null)
            data.getConfig().getList("cosmetics.own.offhand").forEach(key -> {
                OFFHAND.add(Cosmetic.OFFHAND.valueOf((String) key));
            });
    }

    public void saveConfig() {
        List<Object> backCosmeticNames = new ArrayList<>();
        BACK.forEach(cosmetic -> backCosmeticNames.add(cosmetic.name()));
        List<Object> hatCosmeticNames = new ArrayList<>();
        HAT.forEach(cosmetic -> hatCosmeticNames.add(cosmetic.name()));
        List<Object> offhandCosmeticNames = new ArrayList<>();
        OFFHAND.forEach(cosmetic -> offhandCosmeticNames.add(cosmetic.name()));

        data.setList("cosmetics.own.back", backCosmeticNames);
        data.setList("cosmetics.own.hat", hatCosmeticNames);
        data.setList("cosmetics.own.offhand", offhandCosmeticNames);
    }

    public void refreshBack() {
        if (!SkyExcelNetworkCosmeticMain.armorstandManager.containsPlayer(player))
            SkyExcelNetworkCosmeticMain.armorstandManager.addPlayerArmorStand(player);

        SkyExcelNetworkCosmeticMain.armorstandManager.getPlayerArmorStand(player).reloadHelmet();
    }

    public void refreshHat() {
        String hatName = data.getString("cosmetics.wear.hat");

        if (hatName.equals("NONE")) {
            if (player.getInventory().getHelmet() != null) {
                if (Material.IRON_INGOT == player.getInventory().getHelmet().getType()) {
                    if (player.getInventory().getHelmet().getItemMeta().hasCustomModelData()) {
                        player.getInventory().setHelmet(null);
                    }
                }
            }
        } else {
            ItemStack hat = new ItemStack(getWearHatCosmetic().getType(), 1);
            ItemMeta meta = hat.getItemMeta();
            meta.setCustomModelData(getWearHatCosmetic().getCustomModelData());
            meta.setDisplayName(getWearBackCosmetic().getName());
            hat.setItemMeta(meta);

            if (player.getInventory().getHelmet() == null) {
                player.getInventory().setHelmet(hat);
            } else {
                if (Material.IRON_INGOT == player.getInventory().getHelmet().getType()) {
                    if (player.getInventory().getHelmet().getItemMeta().hasCustomModelData())
                        player.getInventory().setHelmet(null);
                } else {
                    if (player.getInventory().getContents().length >= player.getInventory().getMaxStackSize())
                        player.getWorld().dropItem(player.getLocation(), player.getInventory().getHelmet());
                    else
                        player.getInventory().addItem(player.getInventory().getHelmet());

                    player.getInventory().setHelmet(null);
                }
            }

            player.getInventory().setHelmet(hat);
        }
    }

    public void refreshOffhand() {
        String offhandName = data.getString("cosmetics.wear.offhand");

        if (offhandName.equals("NONE")) {
            if (player.getInventory().getItemInOffHand() != null) {
                if (Material.COPPER_INGOT == player.getInventory().getItemInOffHand().getType()) {
                    if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                        player.getInventory().setItemInOffHand(null);
                    }
                }
            }
        } else {
            ItemStack hat = new ItemStack(getWearOffhandCosmetic().getType(), 1);
            ItemMeta meta = hat.getItemMeta();
            meta.setCustomModelData(getWearOffhandCosmetic().getCustomModelData());
            meta.setDisplayName(getWearOffhandCosmetic().getName());
            hat.setItemMeta(meta);

            if (player.getInventory().getItemInOffHand() == null) {
                player.getInventory().setItemInOffHand(hat);
            } else {
                if (Material.COPPER_INGOT == player.getInventory().getItemInOffHand().getType()) {
                    if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
                        player.getInventory().setItemInOffHand(null);
                } else {
                    if (player.getInventory().getContents().length >= player.getInventory().getMaxStackSize())
                        player.getWorld().dropItem(player.getLocation(), player.getInventory().getItemInOffHand());
                    else
                        player.getInventory().addItem(player.getInventory().getItemInOffHand());

                    player.getInventory().setItemInOffHand(null);
                }
            }

            player.getInventory().setItemInOffHand(hat);
        }
    }
}
