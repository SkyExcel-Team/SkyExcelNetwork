package net.skyexcel.server;

import net.skyexcel.server.cmd.MileageCommand;
import net.skyexcel.server.cmd.ShopCommand;
import net.skyexcel.server.event.ShopEvent;
import net.skyexcel.server.event.onJoin;
import net.skyexcel.server.hook.SEConomyExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetwork extends JavaPlugin {

    public static JavaPlugin plugin;

    public static Config message;


    public static Config shop;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        init();
        Listener[] listeners = {new onJoin(), new ShopEvent()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });


    }

    private void init() {

        new SEConomyExpansion(this).register();
        new MileageCommand();
        new ShopCommand();

//
        message = new Config("message");
        message.setPlugin(this);
        message.loadDefaultPluginConfig();

        shop = new Config("shop");
        shop.setPlugin(this);
        shop.loadDefaultPluginConfig();
    }
}
