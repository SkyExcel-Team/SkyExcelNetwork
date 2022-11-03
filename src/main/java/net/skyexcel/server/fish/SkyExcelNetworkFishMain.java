package net.skyexcel.server.fish;

import net.skyexcel.server.fish.cmd.FishCmd;
import net.skyexcel.server.fish.cmd.FishGameCmd;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkFishMain {
    public static JavaPlugin plugin;
    private Config config;

    public SkyExcelNetworkFishMain(JavaPlugin plugin) {
        SkyExcelNetworkFishMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("낚시대회").setExecutor(new FishGameCmd());
        Bukkit.getPluginCommand("낚시").setExecutor(new FishCmd());
    }
}