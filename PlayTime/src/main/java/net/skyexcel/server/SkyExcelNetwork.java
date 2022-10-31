package net.skyexcel.server;

import net.skyexcel.server.cmd.PlayTimeCmd;
import net.skyexcel.server.event.onJoin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetwork extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("접속시간").setExecutor(new PlayTimeCmd());

        Listener[] listeners = {new onJoin()};
        Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }
}
