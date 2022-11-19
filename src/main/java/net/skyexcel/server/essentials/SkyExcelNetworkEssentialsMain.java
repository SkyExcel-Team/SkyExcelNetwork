package net.skyexcel.server.essentials;

import net.skyexcel.server.essentials.farmprotect.SkyExcelNetworkFarmProtectMain;
import net.skyexcel.server.essentials.shiftf.SkyExcelNetworkShiftFMain;
import net.skyexcel.server.essentials.shout.SkyExcelNetworkShoutMain;
import net.skyexcel.server.essentials.trashbin.SkyExcelNetworkTrashBinMain;
import net.skyexcel.server.essentials.util.ChatCoolDownUtils;
import net.skyexcel.server.essentials.whisper.SkyExcelNetworkWhisperMain;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkEssentialsMain {
    public static JavaPlugin plugin;
    public static Config config;
    public static final ChatCoolDownUtils chatCoolDown = new ChatCoolDownUtils();

    public SkyExcelNetworkEssentialsMain(JavaPlugin plugin) {
        SkyExcelNetworkEssentialsMain.plugin = plugin;

        onEnable();
    }

    private void onEnable() {
        config = new Config("Essentials-config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();

        new SkyExcelNetworkFarmProtectMain(plugin);
        new SkyExcelNetworkShiftFMain(plugin);
        new SkyExcelNetworkShoutMain(plugin);
        new SkyExcelNetworkTrashBinMain(plugin);
        new SkyExcelNetworkWhisperMain(plugin);
    }
}
