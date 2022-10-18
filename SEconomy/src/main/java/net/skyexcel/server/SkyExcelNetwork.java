package net.skyexcel.server;

import net.skyexcel.server.cmd.SEconomyCommand;
import net.skyexcel.server.event.onJoin;
import net.skyexcel.server.hook.SEConomyExpansion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetwork extends JavaPlugin {

    public static Plugin plugin;

    public static Config message;

    public static Config shop;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        init();

        Bukkit.getPluginManager().registerEvents(new onJoin(),this);

    }

    private void init() {

        new SEConomyExpansion(this).register();
        new SEconomyCommand();

        message = new Config("message");
        message.setPlugin(this);
        message.loadDefaultPluginConfig();

        shop = new Config("shop");
        shop.setPlugin(this);
        shop.loadDefaultPluginConfig();
    }
}
