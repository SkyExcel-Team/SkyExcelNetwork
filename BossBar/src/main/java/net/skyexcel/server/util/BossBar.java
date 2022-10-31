package net.skyexcel.server.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BossBar {

    private JavaPlugin plugin;

    private org.bukkit.boss.BossBar bar;

    public BossBar(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public BossBar show(String title, BarColor color, BarStyle barStyle, BarFlag... barFlags) {
        bar = Bukkit.createBossBar(title, color, barStyle, barFlags);
        bar.setVisible(true);
        return this;
    }
}
