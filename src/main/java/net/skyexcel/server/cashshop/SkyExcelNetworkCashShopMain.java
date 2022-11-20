package net.skyexcel.server.cashshop;

import net.skyexcel.server.cashshop.cmd.*;
import net.skyexcel.server.cashshop.event.CashListener;
import net.skyexcel.server.cashshop.hook.CashExpansion;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkCashShopMain implements Listener {
    private static JavaPlugin plugin;

    public static Config message;
    public static Config cashShop;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Listener[] listeners = {new CashListener()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );


        plugin.getCommand("캐시").setExecutor(new CashCmd());
        plugin.getCommand("캐시").setTabCompleter(new CashCmdTab());

        plugin.getCommand("캐시상점").setExecutor(new CashShopCmd());
        plugin.getCommand("캐시상점").setTabCompleter(new CashShopCmdTab());

        new CashExpansion(plugin).register();

        message = new Config("CashShop-message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        cashShop = new Config("CashShop-default");
        cashShop.setPlugin(plugin);
        cashShop.loadDefaultPluginConfig();
    }
}
