package net.skyexcel.server.cashprofile;

import net.skyexcel.server.cashprofile.cmd.CashProfileCmd;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.lockmanager.SkyExcelNetworkLockManagerMain;
import net.skyexcel.server.lockmanager.event.SignListener;
import net.skyexcel.server.lockmanager.packet.SignEdit;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkCashProfileMain implements Listener {

    private static JavaPlugin plugin;


    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();


        Bukkit.getLogger().info("LockManager 플러그인 활성화");
        Bukkit.getPluginManager().registerEvents(new SignListener(), plugin);
        plugin.getCommand("캐시프로필").setExecutor(new CashProfileCmd());

    }


}
