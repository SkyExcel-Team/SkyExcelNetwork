package net.skyexcel.server.fish;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.fish.cmd.FishCmd;
import net.skyexcel.server.fish.cmd.FishGameCmd;
import net.skyexcel.server.fish.cmd.test;
import net.skyexcel.server.fish.cmd.testTab;
import net.skyexcel.server.fish.data.FishStatus;
import net.skyexcel.server.fish.event.FishEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkFishMain implements Listener {
    private static JavaPlugin plugin;
    public static FishStatus status = FishStatus.Stop;

    private Config config;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("낚시대회").setExecutor(new FishGameCmd());
        Bukkit.getPluginCommand("낚시").setExecutor(new FishCmd());

        Bukkit.getPluginCommand("test").setExecutor(new test());
        Bukkit.getPluginCommand("test").setTabCompleter(new testTab());

        Listener[] listeners = {new FishEvent()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        });
    }
}