package net.skyexcel.server.warp;

import net.skyexcel.server.warp.cmd.WarpCmd;
import net.skyexcel.server.warp.cmd.WarpSpawnCmd;
import net.skyexcel.server.warp.cmd.WarpTab;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetWorkWarp {

    private JavaPlugin plugin;


    public SkyExcelNetWorkWarp(JavaPlugin plugin) {
        this.plugin = plugin;


        plugin.getCommand("워프").setExecutor(new WarpCmd());

        plugin.getCommand("spawn").setExecutor(new WarpSpawnCmd());

        plugin.getCommand("워프").setTabCompleter(new WarpTab());
    }
}
