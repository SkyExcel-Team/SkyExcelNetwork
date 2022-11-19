package net.skyexcel.server.essentials.farmprotect;

import net.skyexcel.server.essentials.farmprotect.event.FarmBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkFarmProtectMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkFarmProtectMain(JavaPlugin plugin) {
        SkyExcelNetworkFarmProtectMain.plugin = plugin;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getPluginManager().registerEvents(new FarmBreakEvent(), plugin);
    }
}
