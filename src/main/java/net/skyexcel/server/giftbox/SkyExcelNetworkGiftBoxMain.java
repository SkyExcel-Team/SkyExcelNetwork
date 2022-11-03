package net.skyexcel.server.giftbox;

import net.skyexcel.server.giftbox.cmd.GiftBoxCmd;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkGiftBoxMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkGiftBoxMain(JavaPlugin plugin) {
        SkyExcelNetworkGiftBoxMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("선물함").setExecutor(new GiftBoxCmd());
    }
}
