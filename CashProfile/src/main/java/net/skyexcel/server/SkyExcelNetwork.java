package net.skyexcel.server;

import net.skyexcel.server.cmd.CashProfileCmd;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("캐시프로필").setExecutor(new CashProfileCmd());
    }
}
