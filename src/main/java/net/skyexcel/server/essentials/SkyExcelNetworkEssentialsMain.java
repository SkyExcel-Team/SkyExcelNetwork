package net.skyexcel.server.essentials;

import net.skyexcel.server.essentials.trashbin.TrashBinMain;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkEssentialsMain {
    public static JavaPlugin plugin;
    public static Config config;

    public SkyExcelNetworkEssentialsMain(JavaPlugin plugin) {
        SkyExcelNetworkEssentialsMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        config = new Config("Essentials-config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();

        new TrashBinMain(plugin);
    }
}
