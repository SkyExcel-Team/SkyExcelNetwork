package net.skyexcel.server.lockmanager;

import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkLock {
    private static JavaPlugin plugin;



    public SkyExcelNetworkLock(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init(){

    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
