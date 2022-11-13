package net.skyexcel.server.job;

import net.skyexcel.server.job.event.JobListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkJobMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkJobMain(JavaPlugin plugin) {
        SkyExcelNetworkJobMain.plugin = plugin;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JobListener(), plugin);
    }
}
