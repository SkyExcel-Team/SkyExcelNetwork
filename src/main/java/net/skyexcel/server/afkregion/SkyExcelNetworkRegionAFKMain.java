package net.skyexcel.server.afkregion;


import net.skyexcel.server.afkregion.event.AFKDetectListener;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.afkregion.cmd.AFKCmd;
import net.skyexcel.server.afkregion.cmd.AFKCmdTab;
import net.skyexcel.server.afkregion.cmd.AFKShopCmd;
import net.skyexcel.server.afkregion.cmd.AFKShopCmdTab;
import net.skyexcel.server.afkregion.event.AFKListener;
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

        Bukkit.getPluginManager().registerEvents(new AFKDetectListener(), plugin);

        plugin.getCommand("잠수상점").setExecutor(new AFKShopCmd());
        plugin.getCommand("잠수상점").setTabCompleter(new AFKShopCmdTab());

        plugin.getCommand("잠수").setExecutor(new AFKCmd());
        plugin.getCommand("잠수").setTabCompleter(new AFKCmdTab());
    }
}
