package net.skyexcel.server;

import net.skyexcel.server.cmd.GiftBoxCmd;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("선물함").setExecutor(new GiftBoxCmd());
    }
}