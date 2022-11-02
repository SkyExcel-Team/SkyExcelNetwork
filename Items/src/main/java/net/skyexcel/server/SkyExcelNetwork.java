package net.skyexcel.server;

import net.skyexcel.server.cmd.ItemsCmd;
import net.skyexcel.server.cmd.ItemsCmdTab;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("아이템").setExecutor(new ItemsCmd());
        getCommand("아이템").setTabCompleter(new ItemsCmdTab());
    }
}
