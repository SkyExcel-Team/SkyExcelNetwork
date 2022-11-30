package net.skyexcel.server.essentials.shiftf;

import net.skyexcel.server.essentials.shiftf.event.SwapEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkShiftFMain {
    private static JavaPlugin plugin;

    public SkyExcelNetworkShiftFMain(JavaPlugin plugin1) {
        plugin = plugin1;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getPluginManager().registerEvents(new SwapEvent(), plugin);
    }
}
