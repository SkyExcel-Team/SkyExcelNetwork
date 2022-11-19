package net.skyexcel.server.essentials.whisper;

import net.skyexcel.server.essentials.whisper.cmd.WhisperCmdTabComplete;
import net.skyexcel.server.essentials.whisper.cmd.WhisperCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkWhisperMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkWhisperMain(JavaPlugin plugin) {
        SkyExcelNetworkWhisperMain.plugin = plugin;

        onEnable();
    }

    private void onEnable() {
        Bukkit.getPluginCommand("w").setExecutor(new WhisperCommand());
        Bukkit.getPluginCommand("w").setTabCompleter(new WhisperCmdTabComplete());
    }
}
