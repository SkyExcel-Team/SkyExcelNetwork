package net.skyexcel.server.quest;

import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.quest.cmd.QuestCmd;

import net.skyexcel.server.quest.event.QuestListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkDailyQuestMain implements Listener{
    private JavaPlugin plugin;


    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        plugin.getCommand("일일퀘스트").setExecutor(new QuestCmd());
        Listener[] listeners = {new QuestListener()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, plugin);
                }
        );
    }
}
