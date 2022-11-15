package net.skyexcel.server.job;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkJobMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkJobMain(JavaPlugin plugin) {
        SkyExcelNetworkJobMain.plugin = plugin;
        PluginManager pm = Bukkit.getPluginManager();
//        pm.registerEvents(new JobListener(), plugin);

//        init();
    }


    public void init() {
//        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
//            Scarecrow scarecrow = new Scarecrow(offlinePlayer);
//            scarecrow.getCoolTime().runTaskTimer(plugin, 0, 20);
//        }
    }


    public void disable() {

    }
}
