package net.skyexcel.server.trade;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.trade.cmd.TradeCmd;
import net.skyexcel.server.trade.cmd.TradeTab;
import net.skyexcel.server.trade.event.TradeListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkTradeMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("거래").setExecutor(new TradeCmd());
        Bukkit.getPluginCommand("거래").setTabCompleter(new TradeTab());

        Bukkit.getPluginManager().registerEvents(new TradeListener(), plugin);
    }
}
