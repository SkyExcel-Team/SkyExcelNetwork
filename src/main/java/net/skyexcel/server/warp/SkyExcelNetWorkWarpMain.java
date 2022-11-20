package net.skyexcel.server.warp;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.warp.cmd.WarpCmd;
import net.skyexcel.server.warp.cmd.WarpSpawnCmd;
import net.skyexcel.server.warp.cmd.WarpTab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetWorkWarpMain implements Listener {

    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        plugin.getCommand("워프").setExecutor(new WarpCmd());
        plugin.getCommand("워프").setTabCompleter(new WarpTab());

        plugin.getCommand("spawn").setExecutor(new WarpSpawnCmd());
    }
}
