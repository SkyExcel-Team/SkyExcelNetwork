package net.skyexcel.server;

import net.skyexcel.server.cmd.MenuCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

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
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
