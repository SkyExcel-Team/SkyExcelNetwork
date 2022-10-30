package net.skyexcel.server;

import net.skyexcel.server.trade.cmd.TradeCmd;
import net.skyexcel.server.trade.event.closeEvent;
import net.skyexcel.server.trade.event.onClick;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetwork extends JavaPlugin {


    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        getCommand("거래").setExecutor(new TradeCmd());

        Listener[] listeners = {new closeEvent(), new onClick()};
        Arrays.stream(listeners).forEach(listener ->
                Bukkit.getPluginManager().registerEvents(listener, this));

        plugin = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
