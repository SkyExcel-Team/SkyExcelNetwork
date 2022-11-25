package net.skyexcel.server.cosmetic;

import net.skyexcel.server.cosmetic.cmd.CosmeticTestCommand;
import net.skyexcel.server.cosmetic.event.MoveEvent;
import net.skyexcel.server.cosmetic.event.JoinQuitEvent;
import net.skyexcel.server.cosmetic.manager.ArmorStandManager;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyExcelNetworkCosmeticMain implements Listener {
    private static JavaPlugin plugin;
    public static ArmorStandManager armorStandManager;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("cosmt").setExecutor(new CosmeticTestCommand());

        Bukkit.getPluginManager().registerEvents(new MoveEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(), plugin);

        armorStandManager = new ArmorStandManager();
    }
}
