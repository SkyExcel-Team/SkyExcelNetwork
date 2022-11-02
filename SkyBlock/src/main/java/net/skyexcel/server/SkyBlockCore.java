package net.skyexcel.server;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import net.skyexcel.server.cmd.*;
import net.skyexcel.server.event.*;
import net.skyexcel.server.hook.RankExpansion;
import net.skyexcel.server.hook.RankLevelExpansion;
import net.skyexcel.server.hook.RankNameExpansion;
import net.skyexcel.server.hook.SkyBlockVaultExpansion;
import net.skyexcel.server.util.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;
import java.util.Objects;

public class SkyBlockCore extends JavaPlugin implements Listener {

    public static SkyBlockCore plugin;
    private static ProtocolManager protocolManager;

    private static WorldBorderApi worldBorderApi;

    public static Config config;


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

        config = new Config("config");
        config.setPlugin(this);
        
        WorldManager worldManager = new WorldManager();
        worldManager.create();

        new IslandCmd();

        new OtherCmd();
        new IslandAdminCmdTab();
        new IslandAdminCmd();


        Objects.requireNonNull(Bukkit.getServer().getPluginCommand("섬")).setTabCompleter(new IslandCmdTab());
        new SkyBlockVaultExpansion(this).register();


        new RankExpansion(this).register();
        new RankLevelExpansion(this).register();
        new RankNameExpansion(this).register();

        Listener[] listeners = {new onJoin(), new SkyBlockEvent(), new banBlockEvent(), new onHit()};
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
