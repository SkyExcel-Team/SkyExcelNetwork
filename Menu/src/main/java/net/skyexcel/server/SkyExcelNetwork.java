package net.skyexcel.server;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.skyexcel.server.cmd.MenuCommand;
import net.skyexcel.server.event.ClickEvent;
import net.skyexcel.server.gui.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetwork extends JavaPlugin {

    public static SkyExcelNetwork plugin;

    public static Config defaultConfig;
    public static HeadDatabaseAPI hdb;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        new MenuCommand();
        hdb = new HeadDatabaseAPI();

        defaultConfig = new Config("defualt");
        defaultConfig.setPlugin(this);

        defaultConfig.loadDefaultPluginConfig();

        Listener[] listeners = {new ClickEvent(), new PlayerProfile()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, this);
                }
        );

    }


    @Override
    public void onDisable() {
        super.onDisable();
    }
}
