package net.skyexcel.server.essentials.farmprotect;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.essentials.farmprotect.cmd.FarmProtectCmdTabComplete;
import net.skyexcel.server.essentials.farmprotect.cmd.FarmProtectCommand;
import net.skyexcel.server.essentials.farmprotect.event.FarmBreakEvent;
import net.skyexcel.server.essentials.farmprotect.event.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkFarmProtectMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkFarmProtectMain(JavaPlugin plugin) {
        SkyExcelNetworkEssentialsMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("farmprotect").setExecutor(new FarmProtectCommand());
        Bukkit.getPluginCommand("farmprotect").setTabCompleter(new FarmProtectCmdTabComplete());

        Bukkit.getPluginManager().registerEvents(new FarmBreakEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), plugin);
    }
}
