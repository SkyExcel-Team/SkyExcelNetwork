package net.skyexcel.server;

import net.skyexcel.server.cmd.FishCmd;
import net.skyexcel.server.cmd.FishGameCmd;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetwork extends JavaPlugin {
    public static JavaPlugin plugin;

    private Config config;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("낚시대회").setExecutor(new FishGameCmd());
        getCommand("낚시").setExecutor(new FishCmd());
    }
}