package net.skyexcel.server.lockmanager;

import net.skyexcel.server.lockmanager.event.SignListener;
import net.skyexcel.server.lockmanager.packet.SignEdit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkLock {
    private static JavaPlugin plugin;

    private static SignEdit signEdit;


    public SkyExcelNetworkLock(JavaPlugin plugin) {
        SkyExcelNetworkLock.plugin = plugin;
        init();
//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            foo(onlinePlayer);
//        }
    }

    public void init() {
        Bukkit.getConsoleSender().sendMessage("LockManager 플러그인 활성화");
        Bukkit.getPluginManager().registerEvents(new SignListener(), plugin);
        SkyExcelNetworkLock.signEdit = new SignEdit(plugin);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }


    public void foo(Player target) {
        SignEdit.Menu menu = signEdit.newMenu(Arrays.asList("This", "is", "a"))
                .reopenIfFail(true)
                .response((player, strings) -> {
                    if (!strings[3].equalsIgnoreCase("sign")) {
                        player.sendMessage("Wrong!");
                        return false;
                    }
                    return true;
                });

        menu.open(target);
    }

    public static SignEdit getSignEdit() {
        return signEdit;
    }
}
