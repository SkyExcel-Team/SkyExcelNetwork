package net.skyexcel.server.cosmetic.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerCosmeticData {
    private final Player player;

    private final List<Cosmetic.BACK> BACK = new ArrayList<>();
    private final List<Cosmetic.HAT> HAT = new ArrayList<>();
    private final List<Cosmetic.OFFHAND> OFFHAND = new ArrayList<>();

    public PlayerCosmeticData(Player player) {
        this.player = player;
    }

    public PlayerCosmeticData(String name) {
        this.player = Bukkit.getPlayer(name);
    }

    public PlayerCosmeticData(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    public boolean isAllEmpty() {
        return !(!BACK.isEmpty() || !HAT.isEmpty() || !OFFHAND.isEmpty());
    }

    public boolean isEmpty(CosmeticType type) {
        if (type == CosmeticType.BACK) return BACK.isEmpty();
        else if (type == CosmeticType.HAT) return HAT.isEmpty();
        else if (type == CosmeticType.OFFHAND) return OFFHAND.isEmpty();
        else {
            new TypeNotPresentException(type.toString(), new Throwable()).printStackTrace();
            return false;
        }
    }

    public List<Cosmetic.BACK> getBackCosmetics() {
        return BACK;
    }

    public List<Cosmetic.HAT> getHatCosmetics() {
        return HAT;
    }

    public List<Cosmetic.OFFHAND> getOffhandCosmetics() {
        return OFFHAND;
    }

    public void addCosmetic(Cosmetic.BACK cosmetic) {
        BACK.add(cosmetic);
    }

    public void addCosmetic(Cosmetic.HAT cosmetic) {
        HAT.add(cosmetic);
    }

    public void addCosmetic(Cosmetic.OFFHAND cosmetic) {
        OFFHAND.add(cosmetic);
    }
}
