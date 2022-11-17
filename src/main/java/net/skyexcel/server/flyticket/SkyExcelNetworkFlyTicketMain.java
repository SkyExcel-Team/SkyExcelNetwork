package net.skyexcel.server.flyticket;

import net.skyexcel.server.flyticket.cmd.FlyTicketCmd;
import net.skyexcel.server.flyticket.cmd.FlyTicketTab;
import net.skyexcel.server.flyticket.event.FlyTicketListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkFlyTicketMain {

    private JavaPlugin plugin;

    public SkyExcelNetworkFlyTicketMain(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("플라이").setExecutor(new FlyTicketCmd());
        plugin.getCommand("플라이").setTabCompleter(new FlyTicketTab());
        Bukkit.getPluginManager().registerEvents(new FlyTicketListener(), plugin);
    }

}
