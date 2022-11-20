package net.skyexcel.server.essentials;

import net.skyexcel.server.essentials.autoclean.SkyExcelNetworkAutoCleanMain;
import net.skyexcel.server.essentials.events.PluginDisableEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.essentials.farmprotect.SkyExcelNetworkFarmProtectMain;
import net.skyexcel.server.essentials.shiftf.SkyExcelNetworkShiftFMain;
import net.skyexcel.server.essentials.shout.SkyExcelNetworkShoutMain;
import net.skyexcel.server.essentials.trashbin.SkyExcelNetworkTrashBinMain;
import net.skyexcel.server.essentials.util.ChatCoolDownUtils;
import net.skyexcel.server.essentials.whisper.SkyExcelNetworkWhisperMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public class SkyExcelNetworkEssentialsMain implements Listener {
    private static JavaPlugin plugin;
    public static Config config;

    private SkyExcelNetworkAutoCleanMain autoclean;
    public static final ChatCoolDownUtils chatCoolDown = new ChatCoolDownUtils();

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        config = new Config("Essentials-config");
        config.setPlugin(plugin);
        config.loadDefaultPluginConfig();

        autoclean = new SkyExcelNetworkAutoCleanMain(plugin);
        new SkyExcelNetworkFarmProtectMain(plugin);
        new SkyExcelNetworkShiftFMain(plugin);
        new SkyExcelNetworkShoutMain(plugin);
        new SkyExcelNetworkTrashBinMain(plugin);
        new SkyExcelNetworkWhisperMain(plugin);
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        autoclean.disable();
    }
}
