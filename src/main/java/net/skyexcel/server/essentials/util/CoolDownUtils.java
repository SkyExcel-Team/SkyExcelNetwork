package net.skyexcel.server.essentials.util;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoolDownUtils {
    private static final List<Player> coolDownPlayers = new ArrayList<>();

    public static boolean isCoolDownNow(Player player) {
        return coolDownPlayers.contains(player);
    }

    public static void coolDown(Player player) {
        coolDownPlayers.add(player);

        Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkEssentialsMain.plugin, () -> {
            removeCoolDown(player);
        }, SkyExcelNetworkEssentialsMain.config.getConfig().getLong("no_spam.chat.coolTick")); //혹시 몰라 ㅅㅂ
    }

    public static void removeCoolDown(Player player) {
        if (isCoolDownNow(player)) {
            coolDownPlayers.remove(player);
        }
    }
}
