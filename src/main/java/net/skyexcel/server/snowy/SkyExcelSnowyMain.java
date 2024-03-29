package net.skyexcel.server.snowy;

import net.skyexcel.server.essentials.events.PluginDisableEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.snowy.cmd.SnowCmdTab;
import net.skyexcel.server.snowy.cmd.SnowCommand;
import net.skyexcel.server.snowy.scheduler.SnowParticleScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelSnowyMain implements Listener {
    private static JavaPlugin plugin;
    private SnowParticleScheduler task;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("눈").setExecutor(new SnowCommand());
        Bukkit.getPluginCommand("눈").setTabCompleter(new SnowCmdTab());

        task = new SnowParticleScheduler();
        task.runTaskTimerAsynchronously(plugin, 0, 20L * 3);
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        task.cancel();
        Bukkit.getScheduler().cancelTask(task.getTaskId2());
    }
}
