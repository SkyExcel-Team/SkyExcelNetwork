package net.skyexcel.server;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import net.skyexcel.server.cmd.*;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.event.*;
import net.skyexcel.server.hook.RankExpansion;
import net.skyexcel.server.hook.RankLevelExpansion;
import net.skyexcel.server.hook.RankNameExpansion;
import net.skyexcel.server.hook.SkyBlockVaultExpansion;
import net.skyexcel.server.runnable.SkyBlockDelay;
import net.skyexcel.server.util.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;
import java.util.Objects;

public class SkyBlockCore extends JavaPlugin implements Listener {

    public static JavaPlugin plugin;
    private static ProtocolManager protocolManager;

    private static WorldBorderApi worldBorderApi;

    public static Config config;


    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        init();
    }

    public void init() {
        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = getServer().getServicesManager().getRegistration(WorldBorderApi.class);

        if (worldBorderApiRegisteredServiceProvider == null) {
            getLogger().info("API not found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();

        protocolManager = ProtocolLibrary.getProtocolManager();

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

        //TODO 서버가 켜지면 다시 섬 초기화,제거,생성 쿨타임 진행
        for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()){
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(offlinePlayer);
            SkyBlockDelay delay = new SkyBlockDelay(playerData, false);
            delay.runTaskTimer(plugin,0,20);
        }
    }


    public static WorldBorderApi getWorldBorderApi() {
        return worldBorderApi;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
