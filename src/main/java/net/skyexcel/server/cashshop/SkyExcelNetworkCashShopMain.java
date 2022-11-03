package net.skyexcel.server.cashshop;

import net.skyexcel.server.cashshop.command.CashCmd;
import net.skyexcel.server.cashshop.command.CashShopCmd;
import net.skyexcel.server.cashshop.event.JoinEvent;
import net.skyexcel.server.cashshop.event.QuitEvent;
import net.skyexcel.server.cashshop.hook.CashExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkCashShopMain {
    public static JavaPlugin plugin;

    public static Config message;
    public static Config cashShop;

    public SkyExcelNetworkCashShopMain(JavaPlugin plugin) {
        SkyExcelNetworkCashShopMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        init();

        Listener[] listeners = {new JoinEvent(), new QuitEvent()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );

        new CashCmd().registerCmd();
        new CashExpansion(plugin).register();
        CashShopCmd cashShopCmd = new CashShopCmd();
        cashShopCmd.registerCmd();
    }

    private void init() {
        message = new Config("CashShop-message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        cashShop = new Config("CashShop-cashshop");
        cashShop.setPlugin(plugin);
        cashShop.loadDefaultPluginConfig();
    }
}
