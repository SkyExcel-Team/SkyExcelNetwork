package net.skyexcel.server.snowy;

import net.skyexcel.server.snowy.cmd.SnowCmdTab;
import net.skyexcel.server.snowy.cmd.SnowCommand;
import net.skyexcel.server.snowy.scheduler.SnowParticleScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelSnowyMain {
    public static JavaPlugin plugin;

    public SkyExcelSnowyMain(JavaPlugin plugin) {
        SkyExcelSnowyMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        plugin.getCommand("눈").setExecutor(new SnowCommand());
        Bukkit.getPluginCommand("눈").setTabCompleter(new SnowCmdTab());

        new SnowParticleScheduler(plugin);
    }
}
