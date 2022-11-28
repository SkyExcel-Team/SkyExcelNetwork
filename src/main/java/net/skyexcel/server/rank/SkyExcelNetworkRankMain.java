package net.skyexcel.server.rank;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkRankMain implements Listener {

    private static JavaPlugin plugin;

    private static Config config;

    private static Config rankUp;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();
        plugin.getCommand("test").setExecutor(new TestCmd());

        config = new Config("RankUp-Config");
        config.setPlugin(SkyExcelNetworkMain.getPlugin());
        config.loadDefaultPluginConfig();

        rankUp = new Config("RankUp");
        rankUp.setPlugin(SkyExcelNetworkMain.getPlugin());
        rankUp.loadDefaultPluginConfig();
    }

    public static Config getRankUp() {
        return rankUp;
    }
}
