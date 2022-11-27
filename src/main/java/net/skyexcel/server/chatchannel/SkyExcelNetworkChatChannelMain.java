package net.skyexcel.server.chatchannel;

import net.skyexcel.server.chatchannel.cmd.ChatCmd;
import net.skyexcel.server.chatchannel.cmd.ChatTab;
import net.skyexcel.server.chatchannel.event.Chat;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkChatChannelMain implements Listener {
    private static JavaPlugin plugin;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("채팅채널").setExecutor(new ChatCmd());
        Bukkit.getPluginCommand("채팅채널").setTabCompleter(new ChatTab());

        Bukkit.getPluginManager().registerEvents(new Chat(), plugin);
    }
}
