package net.skyexcel.server.essentials.shout;

import net.skyexcel.server.essentials.shout.cmd.ShoutCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkShoutMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkShoutMain(JavaPlugin plugin) {
        SkyExcelNetworkShoutMain.plugin = plugin;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getPluginCommand("shout").setExecutor(new ShoutCommand());
    }
}
