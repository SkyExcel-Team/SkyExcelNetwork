package net.skyexcel.server.chatchannel;

import net.skyexcel.server.chatchannel.cmd.ChatChannelCmd;
import net.skyexcel.server.chatchannel.cmd.ChatChannelTab;
import net.skyexcel.server.chatchannel.cmd.ChatCmd;
import net.skyexcel.server.chatchannel.event.ChatListener;
import net.skyexcel.server.chatchannel.hook.ChatExpansion;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkChatChannelMain implements Listener {
    private static JavaPlugin plugin;


    public static final String split = " `$№$` ";

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        Bukkit.getPluginCommand("채팅채널").setExecutor(new ChatChannelCmd());
        Bukkit.getPluginCommand("채팅").setExecutor(new ChatCmd());
        Bukkit.getPluginCommand("채팅채널").setTabCompleter(new ChatChannelTab());
        new ChatExpansion(plugin).register();
        Bukkit.getPluginManager().registerEvents(new ChatListener(), plugin);
    }
}
