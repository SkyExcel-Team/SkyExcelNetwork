package net.skyexcel.server.essentials.util;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatCoolDownUtils {
    private static final List<Player> coolDownPlayers = new ArrayList<>();

    public static boolean isCoolDownNow(Player player) {
        return coolDownPlayers.contains(player);
    }

    public static void coolDown(Player player, long coolTick) {
        coolDownPlayers.add(player);

        Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkEssentialsMain.plugin, () -> {
            removeCoolDown(player);
        }, coolTick);
    }

    public static void removeCoolDown(Player player) {
        if (isCoolDownNow(player)) {
            coolDownPlayers.remove(player);
        }
    }
}
