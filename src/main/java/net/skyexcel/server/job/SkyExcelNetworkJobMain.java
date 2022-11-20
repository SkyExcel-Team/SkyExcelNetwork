package net.skyexcel.server.job;


import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.job.cmd.FisherCmd;
import net.skyexcel.server.job.cmd.JobCmd;
import net.skyexcel.server.job.cmd.JobTab;
import net.skyexcel.server.job.listener.JobListener;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkJobMain implements Listener {
    private static JavaPlugin plugin;

    public static Config config;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        plugin.getCommand("물통").setExecutor(new FisherCmd());
        plugin.getCommand("직업").setExecutor(new JobCmd());
        plugin.getCommand("직업").setTabCompleter(new JobTab());
//        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
//            Scarecrow scarecrow = new Scarecrow(offlinePlayer);
//            scarecrow.getCoolTime().runTaskTimer(plugin, 0, 20);
//        }

        Bukkit.getPluginManager().registerEvents(new JobListener(), plugin);

        config = new Config("Job-Config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();
    }
}
