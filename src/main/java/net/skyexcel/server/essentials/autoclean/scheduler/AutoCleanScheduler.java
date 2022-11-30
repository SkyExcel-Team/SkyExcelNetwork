package net.skyexcel.server.essentials.autoclean.scheduler;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.essentials.autoclean.util.ClearUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoCleanScheduler extends BukkitRunnable {
    int remaining = 0;

    public AutoCleanScheduler() {
        remaining = SkyExcelNetworkEssentialsMain.config.getInteger("auto_clean.period");
    }

    @Override
    public void run() {
        if (--remaining == 0) {
            int totalRemoved = 0;

            for (World world : Bukkit.getWorlds()) {
                totalRemoved += ClearUtils.clearWorld(world);
            }

            remaining = SkyExcelNetworkEssentialsMain.config.getInteger("auto_clean.period");

            Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.removed")
                    .replace("%removed%", String.valueOf(totalRemoved)));
        } else {
            if (remaining == 20 * 60)
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.20MinRemains"));
            else if (remaining == 10 * 60)
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.10MinRemains"));
            else if (remaining == 5 * 60)
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.5MinRemains"));
            else if (remaining == 60)
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.1MinRemains"));
            else if (remaining == 30)
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.30SecRemains"));
            else if (remaining == 10)
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.10SecRemains"));
            else if (remaining <= 5) {
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages." + remaining + "SecRemains"));
            }
        }
    }
}
