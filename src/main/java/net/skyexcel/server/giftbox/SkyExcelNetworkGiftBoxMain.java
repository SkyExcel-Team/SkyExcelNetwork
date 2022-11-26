package net.skyexcel.server.giftbox;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.giftbox.cmd.GiftBoxCmd;
import net.skyexcel.server.giftbox.cmd.GiftBoxTab;
import net.skyexcel.server.giftbox.event.GiftBoxListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkGiftBoxMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GiftBoxListener(), plugin);
        Bukkit.getPluginCommand("선물함").setExecutor(new GiftBoxCmd());
        Bukkit.getPluginCommand("선물함").setTabCompleter(new GiftBoxTab());
    }
}
