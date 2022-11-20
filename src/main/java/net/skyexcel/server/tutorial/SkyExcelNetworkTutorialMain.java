package net.skyexcel.server.tutorial;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.tutorial.event.TutorialListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkTutorialMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginManager().registerEvents(new TutorialListener(), plugin);
    }
}
