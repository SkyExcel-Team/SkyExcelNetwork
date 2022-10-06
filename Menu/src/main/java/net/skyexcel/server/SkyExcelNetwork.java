package net.skyexcel.server;

import net.skyexcel.server.cmd.MenuCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {

    public static SkyExcelNetwork plugin;
    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        new MenuCommand();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
