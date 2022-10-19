package net.skyexcel.server;

import net.skyexcel.server.event.BlockBreak;
import net.skyexcel.server.event.SkyBlockEvent;
import net.skyexcel.server.event.banBlockEvent;
import net.skyexcel.server.event.onJoin;
import net.skyexcel.server.hook.SkyBlockExpansion;
import net.skyexcel.server.island.IslandAdminCmdTab;
import net.skyexcel.server.island.IslandCmd;
import net.skyexcel.server.island.IslandCmdTab;
import net.skyexcel.server.island.OtherCmd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

public class SkyExcelNetwork extends JavaPlugin implements Listener {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        init();
    }

    public void init() {
        new IslandCmd();
        new IslandCmdTab();
        new OtherCmd();
        new IslandAdminCmdTab();

        new SkyBlockExpansion(this).register();

        Listener[] listeners = {new BlockBreak(), new onJoin(), new SkyBlockEvent(), new banBlockEvent()};
        Arrays.stream(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });

        Config config = new Config("world/");
        config.setPlugin(this);
        for (String name : config.fileListName()) {
            System.out.println(ChatColor.ITALIC + name + ChatColor.GREEN + " 로드 완료!");
        }
    }
}
