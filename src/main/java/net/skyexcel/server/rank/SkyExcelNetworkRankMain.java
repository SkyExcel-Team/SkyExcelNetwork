package net.skyexcel.server.rank;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkRankMain {

    private static JavaPlugin plugin;


    public SkyExcelNetworkRankMain(JavaPlugin plugin) {
        SkyExcelNetworkRankMain.plugin = plugin;
        init();
    }

    public void init() {
        PluginManager pm = Bukkit.getPluginManager();
//        pm.registerEvents(new AFKListener(), plugin);

//        plugin.getCommand("잠수상점").setExecutor(new AFKShopCmd());
//        plugin.getCommand("잠수상점").setTabCompleter(new AFKShopCmdTab());
//        plugin.getCommand("잠수").setExecutor(new AFKCmd());
//        plugin.getCommand("잠수").setTabCompleter(new AFKCmdTab());
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
