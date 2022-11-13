package net.skyexcel.server.mileage;


import net.skyexcel.server.mileage.cmd.MileageCmd;
import net.skyexcel.server.mileage.cmd.MileageCmdTab;
import net.skyexcel.server.mileage.cmd.MileageShopCmd;
import net.skyexcel.server.mileage.cmd.MileageShopCmdTab;
import net.skyexcel.server.mileage.event.*;

import net.skyexcel.server.mileage.hook.MileageExpansion;
import net.skyexcel.server.trade.event.onClick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkMileageMain {
    public static JavaPlugin plugin;

    public static Config message;
    public static Config cashShop;

    public SkyExcelNetworkMileageMain(JavaPlugin plugin) {
        SkyExcelNetworkMileageMain.plugin = plugin;
        
        init();
        System.out.println(ChatColor.GREEN + " 캐시샵 플러그인 등록 완료!");
        plugin.getCommand("마일리지").setTabCompleter(new MileageCmdTab());
        plugin.getCommand("마일리지상점").setTabCompleter(new MileageShopCmdTab());
    }


    private void init() {
        Listener[] listeners = {new JoinEvent(), new QuitEvent(), new CloseEvent(), new ChatEvent(), new ClickEvent()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );

        new MileageCmd().registerCmd();


        new MileageExpansion(plugin).register();

        MileageShopCmd cashShopCmd = new MileageShopCmd();
        cashShopCmd.registerCmd();

        message = new Config("Mileage-Message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        cashShop = new Config("Mileage-Exchange");
        cashShop.setPlugin(plugin);
        cashShop.loadDefaultPluginConfig();
    }
}
