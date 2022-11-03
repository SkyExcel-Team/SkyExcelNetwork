package net.skyexcel.server.mileage;

import net.skyexcel.server.mileage.cmd.MileageCommand;
import net.skyexcel.server.mileage.cmd.ShopCommand;
import net.skyexcel.server.mileage.event.onJoin;
import net.skyexcel.server.mileage.event.ShopEvent;
import net.skyexcel.server.mileage.hook.MileageExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkMileageMain {

    public static JavaPlugin plugin;
    public static Config message;
    public static Config shop;

    public SkyExcelNetworkMileageMain(JavaPlugin plugin) {
        SkyExcelNetworkMileageMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        init();
        Listener[] listeners = {new onJoin(), new ShopEvent()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        });


    }

    private void init() {

        new MileageExpansion(plugin).register();
        new MileageCommand();
        new ShopCommand();

//
        message = new Config("message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        shop = new Config("shop");
        shop.setPlugin(plugin);
        shop.loadDefaultPluginConfig();
    }
}
