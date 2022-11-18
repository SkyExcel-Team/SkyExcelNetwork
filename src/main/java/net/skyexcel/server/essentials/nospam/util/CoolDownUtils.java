package net.skyexcel.server.essentials.nospam.util;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.essentials.nospam.SkyExcelNetworkNoSpamMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoolDownUtils {
    private static final List<Player> coolDownPlayers = new ArrayList<>();

    public static boolean isCoolDownNow(Player player) {
        return coolDownPlayers.contains(player);
    }

    public static void coolDown(Player player) {
        coolDownPlayers.add(player);

        Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkNoSpamMain.plugin, () -> {
            removeCoolDown(player);
        }, SkyExcelNetworkEssentialsMain.config.getLong("no_spam.chat.coolTick"));
    }

    public static void removeCoolDown(Player player) {
        if (isCoolDownNow(player)) {
            coolDownPlayers.remove(player);
        }
    }
}
