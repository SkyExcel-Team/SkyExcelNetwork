package net.skyexcel.server.essentials.nospam;

import net.skyexcel.server.essentials.nospam.event.ChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkNoSpamMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkNoSpamMain(JavaPlugin plugin) {
        SkyExcelNetworkNoSpamMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), plugin);
    }
}
