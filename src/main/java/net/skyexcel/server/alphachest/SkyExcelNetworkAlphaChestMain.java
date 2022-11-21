package net.skyexcel.server.alphachest;

import net.skyexcel.server.alphachest.cmd.AlphaChestCmdTabComplete;
import net.skyexcel.server.alphachest.cmd.AlphaChestCommand;
import net.skyexcel.server.alphachest.event.CloseEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkAlphaChestMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("창고").setExecutor(new AlphaChestCommand());
        Bukkit.getPluginCommand("창고").setTabCompleter(new AlphaChestCmdTabComplete());

        Bukkit.getPluginManager().registerEvents(new CloseEvent(), plugin);
    }
}
