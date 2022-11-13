package net.skyexcel.server.playtime;

import net.skyexcel.server.playtime.cmd.PlayTimeCmd;
import net.skyexcel.server.playtime.event.onJoin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkPlayTimeMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkPlayTimeMain(JavaPlugin plugin) {
        SkyExcelNetworkPlayTimeMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("접속시간").setExecutor(new PlayTimeCmd());

        Listener[] listeners = {new onJoin()};
        Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }
}
