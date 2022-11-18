package net.skyexcel.server.essentials.whisper;

import net.skyexcel.server.essentials.whisper.cmd.WhisperCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkWhisperMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkWhisperMain(JavaPlugin plugin) {
        SkyExcelNetworkWhisperMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("w").setExecutor(new WhisperCommand());
    }
}
