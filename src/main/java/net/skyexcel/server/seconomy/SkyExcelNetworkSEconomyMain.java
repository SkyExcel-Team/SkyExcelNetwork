package net.skyexcel.server.seconomy;

import net.skyexcel.server.seconomy.cmd.EconomyCmd;
import net.skyexcel.server.seconomy.cmd.EConomyCmdTab;
import net.skyexcel.server.seconomy.cmd.EConomyShopCmd;
import net.skyexcel.server.seconomy.cmd.EConomyShopTab;
import net.skyexcel.server.seconomy.event.SEConomyListener;
import net.skyexcel.server.seconomy.hook.SEconomyExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkSEconomyMain {
    public static JavaPlugin plugin;

    public static Config message;
    public static Config cashShop;

    public SkyExcelNetworkSEconomyMain(JavaPlugin plugin) {
        SkyExcelNetworkSEconomyMain.plugin = plugin;

        init();
    }


    private void init() {
        Listener[] listeners = {new SEConomyListener()};

        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );


        plugin.getCommand("돈").setTabCompleter(new EConomyCmdTab());
        plugin.getCommand("상점").setTabCompleter(new EConomyShopTab());

        plugin.getCommand("돈").setExecutor(new EconomyCmd());
        plugin.getCommand("상점").setExecutor(new EConomyShopCmd());

        new SEconomyExpansion(plugin).register();

        message = new Config("SEConomy-message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        cashShop = new Config("SEConomy-defualt");
        cashShop.setPlugin(plugin);
        cashShop.loadDefaultPluginConfig();
    }
}
