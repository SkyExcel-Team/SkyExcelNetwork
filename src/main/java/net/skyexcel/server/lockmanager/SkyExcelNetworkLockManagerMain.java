package net.skyexcel.server.lockmanager;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.lockmanager.event.SignListener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkLockManagerMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getLogger().info("LockManager 플러그인 활성화");
        Bukkit.getPluginManager().registerEvents(new SignListener(), plugin);

    }
}
