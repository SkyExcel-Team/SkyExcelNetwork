package net.skyexcel.server.regionafk;


import net.skyexcel.server.regionafk.cmd.AFKCmd;
import net.skyexcel.server.regionafk.cmd.AFKCmdTab;
import net.skyexcel.server.regionafk.cmd.AFKShopCmd;
import net.skyexcel.server.regionafk.cmd.AFKShopCmdTab;
import net.skyexcel.server.regionafk.event.AFKListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkAFKMain {
    private static JavaPlugin plugin;


    public SkyExcelNetworkAFKMain(JavaPlugin plugin) {
        SkyExcelNetworkAFKMain.plugin = plugin;
        init();
    }

    public void init() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AFKListener(), plugin);

        plugin.getCommand("잠수상점").setExecutor(new AFKShopCmd());
        plugin.getCommand("잠수상점").setTabCompleter(new AFKShopCmdTab());
        plugin.getCommand("잠수").setExecutor(new AFKCmd());
        plugin.getCommand("잠수").setTabCompleter(new AFKCmdTab());
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
