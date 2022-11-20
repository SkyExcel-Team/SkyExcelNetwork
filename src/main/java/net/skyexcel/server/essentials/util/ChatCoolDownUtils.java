package net.skyexcel.server.essentials.util;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatCoolDownUtils {
    private final List<Player> coolDownPlayers = new ArrayList<>();

    public ChatCoolDownUtils() {}

    public boolean isCoolDownNow(Player player) {
        return coolDownPlayers.contains(player);
    }

    public void coolDown(Player player, long coolTick) {
        coolDownPlayers.add(player);

        Bukkit.getScheduler().runTaskLaterAsynchronously(SkyExcelNetworkMain.getPlugin(), () -> {
            removeCoolDown(player);
        }, coolTick);
    }

    public void removeCoolDown(Player player) {
        if (isCoolDownNow(player)) {
            coolDownPlayers.remove(player);
        }
    }
}
