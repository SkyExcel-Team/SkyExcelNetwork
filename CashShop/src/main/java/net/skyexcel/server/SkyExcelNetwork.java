package net.skyexcel.server;

import net.skyexcel.server.command.CashShopCmd;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        new CashShopCmd();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
