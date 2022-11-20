package net.skyexcel.server.trade;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.trade.cmd.TradeCmd;
import net.skyexcel.server.trade.event.CloseEvent;
import net.skyexcel.server.trade.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkTradeMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("거래").setExecutor(new TradeCmd());

        Bukkit.getPluginManager().registerEvents(new CloseEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(), plugin);
    }
}
