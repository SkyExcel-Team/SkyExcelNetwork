package net.skyexcel.server.trade;

import net.skyexcel.server.trade.cmd.TradeCmd;
import net.skyexcel.server.trade.event.closeEvent;
import net.skyexcel.server.trade.event.onClick;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkTradeMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkTradeMain(JavaPlugin plugin) {
        SkyExcelNetworkTradeMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("거래").setExecutor(new TradeCmd());

        Listener[] listeners = {new closeEvent(), new onClick()};
        Arrays.stream(listeners).forEach(listener ->
                Bukkit.getPluginManager().registerEvents(listener, plugin));
    }
}
