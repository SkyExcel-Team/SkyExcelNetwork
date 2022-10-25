package net.skyexcel.server;

import net.skyexcel.server.event.*;
import net.skyexcel.server.hook.SkyBlockExpansion;
import net.skyexcel.server.island.IslandAdminCmdTab;
import net.skyexcel.server.island.IslandCmd;
import net.skyexcel.server.island.OtherCmd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.io.File;
import java.util.Arrays;

public class SkyBlockCore extends JavaPlugin implements Listener {

    public static SkyBlockCore plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        init();


    }

    public void init() {
        new IslandCmd();

        new OtherCmd();
        new IslandAdminCmdTab();

        new SkyBlockExpansion(this).register();

        Listener[] listeners = {new BlockBreak(), new onJoin(), new SkyBlockEvent(), new banBlockEvent(), new onHit()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });


        for (World world : Bukkit.getWorlds()) {
            
            System.out.println(ChatColor.GREEN + world.getName() + " 로드 완료!");
        }

    }
}
