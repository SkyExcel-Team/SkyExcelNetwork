package net.skyexcel.server.discord.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VerifyUtils {
    private static final Map<String, UUID> verifyCodes = new HashMap<>();

    public static void addVerifyCode(String verifyCode, UUID uuid) {
        verifyCodes.put(verifyCode, uuid);
    }

    public static void removeVerifyCode(String verifyCode) {
        verifyCodes.remove(verifyCode);
    }

    public static UUID getPlayerUuid(String verifyCode) {
        return verifyCodes.get(verifyCode);
    }

    public static Boolean containsCode(String verifyCode) {
        for (Map.Entry<String, UUID> entry : verifyCodes.entrySet()) {
            if (entry.getKey().equals(verifyCode)) return true;
        }

        return false;
    }

    public static Boolean containsPlayer(Player player) {
        UUID currentUuid = player.getUniqueId();

        for (Map.Entry<String, UUID> entry : verifyCodes.entrySet()) {
            if (entry.getValue().equals(currentUuid)) return true;
        }

        return false;
    }

    public static Boolean containsPlayer(UUID uuid) {
        for (Map.Entry<String, UUID> entry : verifyCodes.entrySet()) {
            if (entry.getValue().equals(uuid)) return true;
        }

        return false;
    }
}
