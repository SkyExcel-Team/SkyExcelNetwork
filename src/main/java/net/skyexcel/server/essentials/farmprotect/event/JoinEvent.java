package net.skyexcel.server.essentials.farmprotect.event;

import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import skyexcel.data.file.Config;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Config data = new Config("data/" + p.getUniqueId());
        data.setPlugin(SkyExcelNetworkEssentialsMain.plugin);
        data.loadDefualtConfig();

        data.setBoolean("farmprotect", true);
    }
}
