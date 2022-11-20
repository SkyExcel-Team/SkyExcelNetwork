package net.skyexcel.server.giftbox;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.giftbox.cmd.GiftBoxCmd;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkGiftBoxMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("선물함").setExecutor(new GiftBoxCmd());
    }
}
