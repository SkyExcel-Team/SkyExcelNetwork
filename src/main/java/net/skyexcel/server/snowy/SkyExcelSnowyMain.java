package net.skyexcel.server.snowy;

import net.skyexcel.server.snowy.cmd.SnowCmdTab;
import net.skyexcel.server.snowy.cmd.SnowCommand;
import net.skyexcel.server.snowy.scheduler.SnowParticleScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelSnowyMain {
    public static JavaPlugin plugin;
    private SnowParticleScheduler task;
    private int taskId;

    public SkyExcelSnowyMain(JavaPlugin plugin) {
        SkyExcelSnowyMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        Bukkit.getPluginCommand("눈").setExecutor(new SnowCommand());
        Bukkit.getPluginCommand("눈").setTabCompleter(new SnowCmdTab());

        task = new SnowParticleScheduler();
        this.taskId = (task).runTaskTimerAsynchronously(plugin, 0, 20L * 3).getTaskId();
    }

    public void disable() {
        Bukkit.getScheduler().cancelTask(task.getId());
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
