package net.skyexcel.server.essentials.trashbin;

import net.skyexcel.server.essentials.trashbin.cmd.TrashBinCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkTrashBinMain {
    private static JavaPlugin plugin;

    public SkyExcelNetworkTrashBinMain(JavaPlugin plugin1) {
        plugin = plugin1;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getPluginCommand("trashbin").setExecutor(new TrashBinCommand());
    }
}
