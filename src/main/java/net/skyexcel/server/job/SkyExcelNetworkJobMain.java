package net.skyexcel.server.job;


import net.skyexcel.server.job.cmd.FisherCmd;
import net.skyexcel.server.job.cmd.JobCmd;
import net.skyexcel.server.job.listener.JobListener;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkJobMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkJobMain(JavaPlugin plugin) {
        SkyExcelNetworkJobMain.plugin = plugin;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JobListener(), plugin);

        init();
    }


    public void init() {
        plugin.getCommand("물병").setExecutor(new FisherCmd());
        plugin.getCommand("직업").setExecutor(new JobCmd());
//        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
//            Scarecrow scarecrow = new Scarecrow(offlinePlayer);
//            scarecrow.getCoolTime().runTaskTimer(plugin, 0, 20);
//        }
    }


    public void disable() {

    }
}
