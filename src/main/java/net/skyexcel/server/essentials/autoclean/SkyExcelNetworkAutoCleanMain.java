package net.skyexcel.server.essentials.autoclean;

import net.skyexcel.server.essentials.autoclean.scheduler.AutoCleanScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkAutoCleanMain {
    public static JavaPlugin plugin;
    private int taskId = 0;

    public SkyExcelNetworkAutoCleanMain(JavaPlugin plugin) {
        SkyExcelNetworkAutoCleanMain.plugin = plugin;

        onEnable();
    }

    private void onEnable() {
        taskId = new AutoCleanScheduler().runTaskTimerAsynchronously(plugin, 10L, 20L).getTaskId();
    }

    public void disable() {
        if (taskId != 0)
            Bukkit.getScheduler().cancelTask(taskId);
    }
}
