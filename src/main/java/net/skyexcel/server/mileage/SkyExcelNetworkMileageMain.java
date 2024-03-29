package net.skyexcel.server.mileage;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.mileage.cmd.MileageCmd;
import net.skyexcel.server.mileage.cmd.MileageCmdTab;
import net.skyexcel.server.mileage.cmd.MileageShopCmd;
import net.skyexcel.server.mileage.cmd.MileageShopCmdTab;
import net.skyexcel.server.mileage.event.MileageListener;
import net.skyexcel.server.mileage.hook.MileageExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkMileageMain implements Listener {
    private static JavaPlugin plugin;

    public static Config message;
    public static Config cashShop;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Listener[] listeners = {new MileageListener()};

        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );


        plugin.getCommand("마일리지").setTabCompleter(new MileageCmdTab());
        plugin.getCommand("마일리지상점").setTabCompleter(new MileageShopCmdTab());

        plugin.getCommand("마일리지").setExecutor(new MileageCmd());
        plugin.getCommand("마일리지상점").setExecutor(new MileageShopCmd());

        new MileageExpansion(plugin).register();

        message = new Config("Mileage-Message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();
    }
}
