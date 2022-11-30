package net.skyexcel.server.essentials.autoclean;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.essentials.autoclean.scheduler.AutoCleanScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkAutoCleanMain {
    private static JavaPlugin plugin;
    private int taskId = 0;

    public SkyExcelNetworkAutoCleanMain(JavaPlugin plugin1) {
        plugin = plugin1;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () ->
                taskId = new AutoCleanScheduler().runTaskTimer(plugin, SkyExcelNetworkEssentialsMain.config.getLong("auto_clean.period"), 20L).getTaskId());
    }

    public void onDisable() {
        if (taskId != 0)
            Bukkit.getScheduler().cancelTask(taskId);
    }
}
