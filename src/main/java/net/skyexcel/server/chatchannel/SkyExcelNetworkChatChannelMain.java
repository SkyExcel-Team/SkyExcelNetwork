package net.skyexcel.server.chatchannel;

import net.skyexcel.server.chatchannel.cmd.ChatCmd;
import net.skyexcel.server.chatchannel.event.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkChatChannelMain {
    public static JavaPlugin plugin;

    public SkyExcelNetworkChatChannelMain(JavaPlugin plugin) {
        SkyExcelNetworkChatChannelMain.plugin = plugin;

        onEnable();
    }

    public void onEnable() {
        plugin.getCommand("채팅채널").setExecutor(new ChatCmd());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Chat(), plugin);
    }

}
