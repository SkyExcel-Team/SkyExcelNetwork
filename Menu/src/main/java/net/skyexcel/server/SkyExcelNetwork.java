package net.skyexcel.server;

import net.skyexcel.server.cmd.MenuCommand;
import net.skyexcel.server.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetwork extends JavaPlugin {

    public static SkyExcelNetwork plugin;

    public static Config defaultConfig;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        new MenuCommand();

        defaultConfig = new Config("defualt");
        defaultConfig.setPlugin(this);

        defaultConfig.loadDefaultPluginConfig();
        Listener[] listeners = {new ClickEvent()};
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
