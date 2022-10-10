package net.skyexcel.server;

import net.skyexcel.server.island.IslandCmd;
import net.skyexcel.server.island.IslandCmdTab;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetwork extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        new IslandCmd();
        new IslandCmdTab();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
