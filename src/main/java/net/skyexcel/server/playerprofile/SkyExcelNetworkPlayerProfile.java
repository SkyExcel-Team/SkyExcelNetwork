package net.skyexcel.server.playerprofile;

import net.skyexcel.server.playerprofile.cmd.PlayerProfileCmd;
import net.skyexcel.server.playerprofile.cmd.PlayerProfileTab;
import net.skyexcel.server.playerprofile.cmd.ReputationCmd;
import net.skyexcel.server.playerprofile.cmd.ReputationTab;
import net.skyexcel.server.playerprofile.data.PlayerProfile;
import net.skyexcel.server.playerprofile.event.PlayerProfileListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkPlayerProfile {

    private JavaPlugin plugin;

    public SkyExcelNetworkPlayerProfile(JavaPlugin plugin) {
        this.plugin = plugin;
        init();
    }

    private void init() {
        plugin.getCommand("프로필").setExecutor(new PlayerProfileCmd());

        plugin.getCommand("프로필").setTabCompleter(new PlayerProfileTab());

        plugin.getCommand("인기도").setExecutor(new ReputationCmd());

        plugin.getCommand("인기도").setTabCompleter(new ReputationTab());

        new PlayerProfileListener(plugin);


    }
}
