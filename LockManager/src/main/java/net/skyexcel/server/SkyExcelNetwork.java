package net.skyexcel.server;

import net.skyexcel.server.event.InteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        SignGUI.registerSignUpdateListener(this);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
    }
}
