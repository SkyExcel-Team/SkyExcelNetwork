package net.skyexcel.server.flyticket;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.flyticket.cmd.FlyTicketCmd;
import net.skyexcel.server.flyticket.cmd.FlyTicketTab;
import net.skyexcel.server.flyticket.event.FlyTicketListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkFlyTicketMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        plugin.getCommand("플라이").setExecutor(new FlyTicketCmd());
        plugin.getCommand("플라이").setTabCompleter(new FlyTicketTab());

        Bukkit.getPluginManager().registerEvents(new FlyTicketListener(), plugin);
    }
}
