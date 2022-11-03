package net.skyexcel.server.items;

import net.skyexcel.server.items.cmd.ItemsCmd;
import net.skyexcel.server.items.cmd.ItemsCmdTab;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkItemsMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkItemsMain(JavaPlugin plugin) {
        SkyExcelNetworkItemsMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("아이템").setExecutor(new ItemsCmd());
        Bukkit.getPluginCommand("아이템").setTabCompleter(new ItemsCmdTab());
    }
}
