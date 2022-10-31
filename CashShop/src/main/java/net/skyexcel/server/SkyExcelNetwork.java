package net.skyexcel.server;

import net.skyexcel.server.command.CashCmd;
import net.skyexcel.server.command.CashShopCmd;
import net.skyexcel.server.event.JoinEvent;
import net.skyexcel.server.event.QuitEvent;
import net.skyexcel.server.hook.CashExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

import java.util.Arrays;

//        # +----------------------------------------------------------------------------------------------+ #
//        # │                                                                                              │ #
//        # │                                                                                              │ #
//        # │               ███████╗██╗  ██╗██╗   ██╗███████╗██╗  ██╗ ██████╗███████╗██╗                   │ #
//        # │               ██╔════╝██║ ██╔╝╚██╗ ██╔╝██╔════╝╚██╗██╔╝██╔════╝██╔════╝██║                   │ #
//        # │               ███████╗█████╔╝  ╚████╔╝ █████╗   ╚███╔╝ ██║     █████╗  ██║                   │ #
//        # │               ╚════██║██╔═██╗   ╚██╔╝  ██╔══╝   ██╔██╗ ██║     ██╔══╝  ██║                   │ #
//        # │               ███████║██║  ██╗   ██║   ███████╗██╔╝ ██╗╚██████╗███████╗███████╗              │ #
//        # │               ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝╚══════╝╚══════╝              │ #
//        # │                                                                                              │ #
//        # │                          ■ WebSite: https://skyexcel.net                                     │ #
//        # │                          ■ Discord: https://discord.gg/TMaAyJsEnQ                            │ #
//        # │                          ■ Version: v0.1                                                     │ #
//        # │                          ■ Authors: @Yooinseo @idkNicks                                      │ #
//        # │                                                                                              │ #
//        # │                                                                                              │ #
//        # │                  ■  Copyright © 2022 SkyExcel Team - All Rights Reserved  ■                  │ #
//        # │                                                                                              │ #
//        # +----------------------------------------------------------------------------------------------+ #
//        ####################################################################################################
public class SkyExcelNetwork extends JavaPlugin {
    public static Plugin plugin;

    public static Config message;

    public static Config cashShop;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;


        init();

        Listener[] listeners = {new JoinEvent(), new QuitEvent()};
        Arrays.stream(listeners).forEach(listener -> {
                    Bukkit.getPluginManager().registerEvents(listener, this);
                }
        );

        new CashCmd().registerCmd();
        new CashExpansion(this).register();
        CashShopCmd cashShopCmd = new CashShopCmd();
        cashShopCmd.registerCmd();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void init() {
        message = new Config("message");
        message.setPlugin(this);
        message.loadDefaultPluginConfig();

        cashShop = new Config("cashshop");
        cashShop.setPlugin(this);
        cashShop.loadDefaultPluginConfig();
    }
}
