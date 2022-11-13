package net.skyexcel.server.tutorial;

import net.skyexcel.server.tutorial.event.TutorialListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkTutorialMain {
    public static JavaPlugin plugin;


    public SkyExcelNetworkTutorialMain(JavaPlugin plugin) {
        SkyExcelNetworkTutorialMain.plugin = plugin;
        PluginManager pm = Bukkit.getPluginManager();
        Listener[] listeners = {new TutorialListener()};
        Arrays.stream(listeners).forEach(listener -> {
            pm.registerEvents(listener, plugin);
        });
    }
}