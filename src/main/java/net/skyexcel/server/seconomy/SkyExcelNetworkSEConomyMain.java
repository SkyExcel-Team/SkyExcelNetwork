package net.skyexcel.server.seconomy;

import net.skyexcel.server.seconomy.cmd.EconomyCmdTab;
import net.skyexcel.server.seconomy.cmd.SEconomyCommand;
import net.skyexcel.server.seconomy.cmd.ShopCommand;
import net.skyexcel.server.seconomy.event.ShopEvent;
import net.skyexcel.server.seconomy.event.onJoin;
import net.skyexcel.server.seconomy.hook.SEConomyExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkSEConomyMain {

    public static JavaPlugin plugin;

    public static Config message;
    public static Config shop;

    public SkyExcelNetworkSEConomyMain(JavaPlugin plugin) {
        SkyExcelNetworkSEConomyMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        init();
        Listener[] listeners = { new onJoin(),new ShopEvent()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        });


    }

    private void init() {

        new SEConomyExpansion(plugin).register();
        new SEconomyCommand();
        plugin.getCommand("돈").setTabCompleter(new EconomyCmdTab());
        new ShopCommand();
//
        message = new Config("SEConomy-message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        shop = new Config("SEConomy-shop");
        shop.setPlugin(plugin);
        shop.loadDefaultPluginConfig();
    }
}
