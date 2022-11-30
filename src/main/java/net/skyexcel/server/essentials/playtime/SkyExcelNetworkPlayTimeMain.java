package net.skyexcel.server.essentials.playtime;

import net.skyexcel.server.essentials.playtime.cmd.PlayTimeCmd;
import net.skyexcel.server.essentials.playtime.event.PlayTimeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkPlayTimeMain {
    private static JavaPlugin plugin;

    public SkyExcelNetworkPlayTimeMain(JavaPlugin  plugin1) {
        plugin = plugin1;

        Bukkit.getPluginCommand("접속시간").setExecutor(new PlayTimeCmd());

        Bukkit.getPluginManager().registerEvents(new PlayTimeListener(), plugin);
    }
}
