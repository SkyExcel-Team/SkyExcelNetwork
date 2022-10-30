package net.skyexcel.server;

import net.skyexcel.server.cmd.ChatCmd;
import net.skyexcel.server.event.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatChannel extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        new ChatCmd(this);

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Chat(), this);
    }

}
