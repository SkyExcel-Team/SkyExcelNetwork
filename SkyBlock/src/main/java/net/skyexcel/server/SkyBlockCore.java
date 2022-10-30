package net.skyexcel.server;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import net.skyexcel.server.event.*;
import net.skyexcel.server.hook.SkyBlockExpansion;
import net.skyexcel.server.island.IslandAdminCmdTab;
import net.skyexcel.server.island.IslandCmd;
import net.skyexcel.server.island.OtherCmd;
import net.skyexcel.server.util.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyBlockCore extends JavaPlugin implements Listener {

    public static SkyBlockCore plugin;
    private static ProtocolManager protocolManager;

    private static WorldBorderApi worldBorderApi;


    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = getServer().getServicesManager().getRegistration(WorldBorderApi.class);

        if (worldBorderApiRegisteredServiceProvider == null) {
            getLogger().info("API not found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();
        init();


        protocolManager = ProtocolLibrary.getProtocolManager();


    }

    public void init() {

        WorldManager worldManager = new WorldManager();
        worldManager.create();

        new IslandCmd();

        new OtherCmd();

        new SkyBlockExpansion(this).register();

        Listener[] listeners = { new onJoin(), new SkyBlockEvent(), new banBlockEvent(), new onHit()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });


        for (World world : Bukkit.getWorlds()) {

            System.out.println(ChatColor.GREEN + world.getName() + " 로드 완료!");
        }

    }


    public static WorldBorderApi getWorldBorderApi() {
        return worldBorderApi;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
