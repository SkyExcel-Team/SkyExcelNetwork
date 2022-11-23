package net.skyexcel.server.menu;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.menu.cmd.MenuCommand;
import net.skyexcel.server.menu.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkMenuMain implements Listener {

    private static JavaPlugin plugin;

    public static Config defaultConfig;


    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        new MenuCommand();


        defaultConfig = new Config("Menu-defualt");
        defaultConfig.setPlugin(plugin);

        defaultConfig.loadDefaultPluginConfig();

        Listener[] listeners = {new ClickEvent() };
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );
    }
}
