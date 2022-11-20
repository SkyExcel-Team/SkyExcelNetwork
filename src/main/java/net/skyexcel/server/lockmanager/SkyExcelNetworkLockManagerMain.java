package net.skyexcel.server.lockmanager;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.lockmanager.event.SignListener;
import net.skyexcel.server.lockmanager.packet.SignEdit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkLockManagerMain implements Listener {
    private static JavaPlugin plugin;

    private static SignEdit signEdit;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            foo(onlinePlayer);
//        }

        Bukkit.getLogger().info("LockManager 플러그인 활성화");
        Bukkit.getPluginManager().registerEvents(new SignListener(), plugin);
        SkyExcelNetworkLockManagerMain.signEdit = new SignEdit(plugin);
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
