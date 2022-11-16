package net.skyexcel.server.skyblock;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import net.skyexcel.server.skyblock.cmd.IslandAdminCmd;
import net.skyexcel.server.skyblock.cmd.IslandAdminCmdTab;
import net.skyexcel.server.skyblock.cmd.IslandCmd;
import net.skyexcel.server.skyblock.cmd.IslandCmdTab;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import net.skyexcel.server.skyblock.event.*;
import net.skyexcel.server.skyblock.hook.RankExpansion;
import net.skyexcel.server.skyblock.hook.RankLevelExpansion;
import net.skyexcel.server.skyblock.hook.RankNameExpansion;
import net.skyexcel.server.skyblock.hook.SkyBlockVaultExpansion;
import net.skyexcel.server.skyblock.runnable.SkyBlockDelay;
import net.skyexcel.server.skyblock.ui.armorstand.RankStand;
import net.skyexcel.server.skyblock.util.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetworkSkyBlockMain implements Listener {

    public static JavaPlugin plugin;
    public static Config config;

    private static ProtocolManager protocolManager;
    private static WorldBorderApi worldBorderApi;

    public SkyExcelNetworkSkyBlockMain(JavaPlugin newplugin) {
        plugin = newplugin;
        init();
    }


    public void init() {
        RankStand rankStand = new RankStand();
//        rankStand.create();

        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = Bukkit.getServer().getServicesManager().getRegistration(WorldBorderApi.class);

        if (worldBorderApiRegisteredServiceProvider == null) {
            Bukkit.getLogger().warning("WorldBorderAPI가 감지되지 않았습니다.");
            Bukkit.getLogger().warning("플러그인을 비활성화 합니다.");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();

        protocolManager = ProtocolLibrary.getProtocolManager();

        config = new Config("config");
        config.setPlugin(plugin);

        WorldManager worldManager = new WorldManager();
        worldManager.create();

        plugin.getCommand("섬").setExecutor(new IslandCmd());

        Bukkit.getServer().getPluginCommand("섬").setTabCompleter(new IslandCmdTab());


        plugin.getCommand("섬어드민").setExecutor(new IslandAdminCmd());
        Bukkit.getServer().getPluginCommand("섬어드민").setTabCompleter(new IslandAdminCmdTab());

        new SkyBlockVaultExpansion(plugin).register();
        new RankExpansion(plugin).register();
        new RankLevelExpansion(plugin).register();
        new RankNameExpansion(plugin).register();

        Listener[] listeners = {new onJoin(), new SkyBlockListener(), new banBlockEvent(), new onHit(), new onQuit()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        });

        for (World world : Bukkit.getWorlds()) {
            System.out.println(ChatColor.GREEN + world.getName() + " 로드 완료!");
        }

        //TODO 서버가 켜지면 다시 섬 초기화,제거,생성 쿨타임 진행
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(offlinePlayer);
            SkyBlockDelay delay = new SkyBlockDelay(playerData, false);
            delay.runTaskTimer(plugin, 0, 20);
        }

    }


    public static WorldBorderApi getWorldBorderApi() {
        return worldBorderApi;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
