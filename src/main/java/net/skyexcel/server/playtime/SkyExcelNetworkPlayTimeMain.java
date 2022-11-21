package net.skyexcel.server.playtime;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.playtime.cmd.PlayTimeCmd;
import net.skyexcel.server.playtime.event.PlayTimeListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkPlayTimeMain implements Listener {
    public static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("접속시간").setExecutor(new PlayTimeCmd());

        Bukkit.getPluginManager().registerEvents(new PlayTimeListener(), plugin);

    }
}
