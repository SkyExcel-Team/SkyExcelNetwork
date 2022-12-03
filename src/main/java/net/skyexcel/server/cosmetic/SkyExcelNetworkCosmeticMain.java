package net.skyexcel.server.cosmetic;

import net.skyexcel.server.cosmetic.cmd.CosmeticCmdTabComplete;
import net.skyexcel.server.cosmetic.cmd.CosmeticCommand;
import net.skyexcel.server.cosmetic.event.*;
import net.skyexcel.server.cosmetic.manager.ArmorStandManager;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkCosmeticMain implements Listener {
    private static JavaPlugin plugin;
    public static ArmorStandManager armorstandManager;

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        plugin = e.getPlugin();

        //Initializing
        Bukkit.getOnlinePlayers().forEach(player -> player.getPassengers().forEach(Entity::remove));

        //Commands
        Bukkit.getPluginCommand("코스튬").setExecutor(new CosmeticCommand());
        Bukkit.getPluginCommand("코스튬").setTabCompleter(new CosmeticCmdTabComplete());

        //Event
        Bukkit.getPluginManager().registerEvents(new GuiInventoryListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new EntityListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), plugin);

        //Initialize Util
        armorstandManager = new ArmorStandManager();
    }
}
