package net.skyexcel.server.essentials.autoclean.scheduler;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoCleanScheduler extends BukkitRunnable {
    int remaining = 0;

    public AutoCleanScheduler() {
        remaining = SkyExcelNetworkEssentialsMain.config.getInteger("auto_clean.period");
    }

    @Override
    public void run() {
        if (--remaining == 0) {
            int removed = 0;

            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntitiesByClass(Item.class)) {
                    entity.remove();
                    removed++;
                }
            }

            remaining = SkyExcelNetworkEssentialsMain.config.getInteger("auto_clean.period");

            Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.removed")
                    .replace("%removed%", String.valueOf(removed)));
        } else {
            if (remaining == 30) {
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.30SecRemains"));
            } else if (remaining == 10) {
                Bukkit.broadcastMessage(SkyExcelNetworkEssentialsMain.config.getString("auto_clean.messages.10SecRemains"));
            }
        }
    }
}
