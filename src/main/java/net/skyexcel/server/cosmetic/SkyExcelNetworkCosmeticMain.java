package net.skyexcel.server.cosmetic;

import net.skyexcel.server.cosmetic.cmd.CosmeticTestCommand;
import net.skyexcel.server.cosmetic.data.PlayerCosmeticData;
import net.skyexcel.server.cosmetic.event.InventoryEvent;
import net.skyexcel.server.cosmetic.event.PlayerListener;
import net.skyexcel.server.cosmetic.event.JoinQuitEvent;
import net.skyexcel.server.cosmetic.manager.ArmorStandManager;
import net.skyexcel.server.cosmetic.scheduler.ArmorStandMoveListenScheduler;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyExcelNetworkCosmeticMain implements Listener {
    private static JavaPlugin plugin;
    public static ArmorStandManager armorstandManager;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("cosmt").setExecutor(new CosmeticTestCommand());

        Bukkit.getPluginManager().registerEvents(new InventoryEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(), plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                new ArmorStandMoveListenScheduler().runTaskTimer(plugin, 0L, 5L);
            }
        }.runTaskAsynchronously(plugin);

        armorstandManager = new ArmorStandManager();
    }
}
