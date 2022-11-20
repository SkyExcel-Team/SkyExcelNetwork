package net.skyexcel.server.items;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.items.cmd.ItemsCmd;
import net.skyexcel.server.items.cmd.ItemsCmdTab;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkItemsMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin =  e.getPlugin();

        Bukkit.getPluginCommand("아이템").setExecutor(new ItemsCmd());
        Bukkit.getPluginCommand("아이템").setTabCompleter(new ItemsCmdTab());
    }
}
