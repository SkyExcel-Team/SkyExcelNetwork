package net.skyexcel.server.menu;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.skyexcel.server.cmd.MenuCommand;
import net.skyexcel.server.event.ClickEvent;
import net.skyexcel.server.gui.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkMenuMain {

    public static JavaPlugin plugin;

    public static Config defaultConfig;
    public static HeadDatabaseAPI hdb;

    public SkyExcelNetworkMenuMain(JavaPlugin plugin) {
        SkyExcelNetworkMenuMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        new MenuCommand();
        hdb = new HeadDatabaseAPI();

        defaultConfig = new Config("Menu-defualt");
        defaultConfig.setPlugin(plugin);

        defaultConfig.loadDefaultPluginConfig();

        Listener[] listeners = {new ClickEvent(), new PlayerProfile()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );

    }
}
