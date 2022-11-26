package net.skyexcel.server.cosmetic.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    public PlayerCosmeticData(String name) {
        this.player = Bukkit.getPlayer(name);

        loadConfig();
    }

    public PlayerCosmeticData(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);

        loadConfig();
    }

    public boolean isAllEmpty() {
        reloadConfig();

        return !(!BACK.isEmpty() || !HAT.isEmpty() || !OFFHAND.isEmpty());
    }

    public boolean isEmpty(CosmeticType type) {
        reloadConfig();

        if (type == CosmeticType.BACK) return BACK.isEmpty();
        else if (type == CosmeticType.HAT) return HAT.isEmpty();
        else if (type == CosmeticType.OFFHAND) return OFFHAND.isEmpty();
        else return false;
    }

    public List<Cosmetic.BACK> getBackCosmetics() {
        reloadConfig();

        return BACK;
    }

    public List<Cosmetic.HAT> getHatCosmetics() {
        reloadConfig();

        return HAT;
    }

    public List<Cosmetic.OFFHAND> getOffhandCosmetics() {
        reloadConfig();

        return OFFHAND;
    }

    public void addCosmetic(Cosmetic.BACK cosmetic) {
        reloadConfig();

        BACK.add(cosmetic);
    }

    public void addCosmetic(Cosmetic.HAT cosmetic) {
        reloadConfig();

        HAT.add(cosmetic);
    }

    public void addCosmetic(Cosmetic.OFFHAND cosmetic) {
        reloadConfig();

        OFFHAND.add(cosmetic);
    }

    public void setWearBack(Cosmetic.BACK wearBack) {
        reloadConfig();

        data.setString("cosmetics.wear.back", wearBack.name());
    }

    public void setWearHat(Cosmetic.HAT wearHat) {
        reloadConfig();

        data.setString("cosmetics.wear.hat", wearHat.name());
    }

    public void setWearOffhand(Cosmetic.OFFHAND wearOffhand) {
        reloadConfig();

        data.setString("cosmetics.wear.offhand", wearOffhand.name());
    }

    public Cosmetic.BACK getWearBackCosmetic() {
        reloadConfig();

        return Cosmetic.BACK.valueOf(data.getString("cosmetics.wear.back"));
    }

    public Cosmetic.HAT getWearHatCosmetic() {
        reloadConfig();

        return Cosmetic.HAT.valueOf(data.getString("cosmetics.wear.hat"));
    }

    public Cosmetic.OFFHAND getWearOffhandCosmetic() {
        reloadConfig();

        return Cosmetic.OFFHAND.valueOf(data.getString("cosmetics.wear.offhand"));
    }

    private void reloadConfig() {
        if (data == null) {
            loadConfig();
        }

        data.reloadConfig();
    }

    private void loadConfig() {
        data = new Config("data/" + this.player.getUniqueId());
        data.setPlugin(SkyExcelNetworkMain.getPlugin());

        if (!data.isFileExist()) {
            data.setString("cosmetics.wear.back", "none");
            data.setString("cosmetics.wear.hat", "none");
            data.setString("cosmetics.wear.offhand", "none");

            data.setList("cosmetics.own.back", List.of("none"));
            data.setList("cosmetics.own.hat", List.of("none"));
            data.setList("cosmetics.own.offhand", List.of("none"));
        }
    }
}
