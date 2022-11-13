package net.skyexcel.server.cashshop;

import net.skyexcel.server.cashshop.cmd.*;
import net.skyexcel.server.cashshop.event.CashListener;
import net.skyexcel.server.cashshop.hook.CashExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        init();
        System.out.println(ChatColor.GREEN + " 캐시샵 플러그인 등록 완료!");
        plugin.getCommand("캐시").setTabCompleter(new CashCmdTab());
        plugin.getCommand("캐시상점").setTabCompleter(new CashShopCmdTab());
    }


    private void init() {
        Listener[] listeners = {new CashListener()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );

        new CashCmd().registerCmd();

        new CashExpansion(plugin).register();

        CashShopCmd cashShopCmd = new CashShopCmd();
        cashShopCmd.registerCmd();

        message = new Config("CashShop-message");
        message.setPlugin(plugin);
        message.loadDefaultPluginConfig();

        cashShop = new Config("CashShop-defualt");
        cashShop.setPlugin(plugin);
        cashShop.loadDefaultPluginConfig();
    }
}
