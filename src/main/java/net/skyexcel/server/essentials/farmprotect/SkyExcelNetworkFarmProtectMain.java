package net.skyexcel.server.essentials.farmprotect;

import net.skyexcel.server.essentials.farmprotect.event.FarmBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkFarmProtectMain {
    private static JavaPlugin plugin;

    public SkyExcelNetworkFarmProtectMain(JavaPlugin plugin1) {
        plugin = plugin1;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getPluginManager().registerEvents(new FarmBreakEvent(), plugin);
    }
}
