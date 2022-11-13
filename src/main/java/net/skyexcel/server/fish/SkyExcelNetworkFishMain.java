package net.skyexcel.server.fish;

import net.skyexcel.server.fish.cmd.FishCmd;
import net.skyexcel.server.fish.cmd.FishGameCmd;
import net.skyexcel.server.fish.data.FishStatus;
import net.skyexcel.server.fish.event.FishEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkFishMain {
    public static JavaPlugin plugin;
    public static FishStatus status = FishStatus.Stop;

    private Config config;

    public SkyExcelNetworkFishMain(JavaPlugin plugin) {
        SkyExcelNetworkFishMain.plugin = plugin;

        init();
    }

    public void init() {
        Bukkit.getPluginCommand("낚시대회").setExecutor(new FishGameCmd());
        Bukkit.getPluginCommand("낚시").setExecutor(new FishCmd());
        Listener[] listeners = {new FishEvent()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        });
    }
}