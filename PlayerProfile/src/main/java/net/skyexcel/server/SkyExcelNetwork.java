package net.skyexcel.server;

import net.skyexcel.server.event.OnPlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        Bukkit.getPluginManager().registerEvents(new OnPlayerInteractEvent(), this);

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
