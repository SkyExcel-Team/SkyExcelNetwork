package net.skyexcel.server.snowy.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SnowParticleScheduler {
    private JavaPlugin plugin;

    private int x = -2;
    private int y = 96;
    private int z = 70;

    public SnowParticleScheduler(JavaPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {

        }, 0L, 20L * 3);
    }
}
