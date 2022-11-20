package net.skyexcel.server.regionafk;


import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.regionafk.cmd.AFKCmd;
import net.skyexcel.server.regionafk.cmd.AFKCmdTab;
import net.skyexcel.server.regionafk.cmd.AFKShopCmd;
import net.skyexcel.server.regionafk.cmd.AFKShopCmdTab;
import net.skyexcel.server.regionafk.event.AFKListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkRegionAFKMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginManager().registerEvents(new AFKListener(), plugin);

        plugin.getCommand("잠수상점").setExecutor(new AFKShopCmd());
        plugin.getCommand("잠수상점").setTabCompleter(new AFKShopCmdTab());

        plugin.getCommand("잠수").setExecutor(new AFKCmd());
        plugin.getCommand("잠수").setTabCompleter(new AFKCmdTab());
    }
}
