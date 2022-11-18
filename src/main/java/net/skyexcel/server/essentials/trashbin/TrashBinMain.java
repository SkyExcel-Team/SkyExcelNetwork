package net.skyexcel.server.essentials.trashbin;

import net.skyexcel.server.essentials.trashbin.cmd.TrashBinCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TrashBinMain {
    public static JavaPlugin plugin;

    public TrashBinMain(JavaPlugin plugin) {
        TrashBinMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("trashbin").setExecutor(new TrashBinCommand());
    }
}
