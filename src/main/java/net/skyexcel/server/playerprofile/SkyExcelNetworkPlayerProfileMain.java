package net.skyexcel.server.playerprofile;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.playerprofile.cmd.PlayerProfileCmd;
import net.skyexcel.server.playerprofile.cmd.PlayerProfileTab;
import net.skyexcel.server.playerprofile.cmd.ReputationCmd;
import net.skyexcel.server.playerprofile.cmd.ReputationTab;
import net.skyexcel.server.playerprofile.event.PlayerProfileListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkPlayerProfileMain implements Listener {

    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        plugin.getCommand("프로필").setExecutor(new PlayerProfileCmd());
        plugin.getCommand("프로필").setTabCompleter(new PlayerProfileTab());

        plugin.getCommand("인기도").setExecutor(new ReputationCmd());
        plugin.getCommand("인기도").setTabCompleter(new ReputationTab());

        new PlayerProfileListener(plugin);
    }
}
