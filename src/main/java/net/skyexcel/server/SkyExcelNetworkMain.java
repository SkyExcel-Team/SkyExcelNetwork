package net.skyexcel.server;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.chatchannel.SkyExcelNetworkChatChannelMain;
import net.skyexcel.server.discord.SkyExcelNetworkDiscordMain;
import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.giftbox.SkyExcelNetworkGiftBoxMain;
import net.skyexcel.server.items.SkyExcelNetworkItemsMain;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.lockmanager.SkyExcelNetworkLockManagerMain;
import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.playerprofile.SkyExcelNetworkPlayerProfile;
import net.skyexcel.server.playtime.SkyExcelNetworkPlayTimeMain;

import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.snowy.SkyExcelSnowyMain;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import net.skyexcel.server.tutorial.SkyExcelNetworkTutorialMain;
import net.skyexcel.server.warp.SkyExcelNetWorkWarp;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkMain extends JavaPlugin {
    private static JavaPlugin plugin;
    public static HeadDatabaseAPI hdb;

    private volatile SkyExcelNetworkDiscordMain discord = null;
    private SkyExcelNetworkJobMain skyExcelNetworkJobMain;

    @Override
    public void onEnable() {
        plugin = this;

        hdb = new HeadDatabaseAPI();

        new SkyExcelNetworkChatChannelMain(plugin);
        new SkyExcelNetworkCashShopMain(plugin);
//        discord = new SkyExcelNetworkDiscordMain(plugin);
        new SkyExcelNetworkFishMain(plugin);
        new SkyExcelNetworkGiftBoxMain(plugin);
        new SkyExcelNetworkItemsMain(plugin);
        this.skyExcelNetworkJobMain = new SkyExcelNetworkJobMain(plugin);
        new SkyExcelNetworkLockManagerMain(plugin);
        new SkyExcelNetworkMenuMain(plugin);
        new SkyExcelNetworkMileageMain(plugin);
        new SkyExcelNetworkPlayerProfile(plugin);
        new SkyExcelNetworkPlayTimeMain(plugin);
        new SkyExcelNetworkSEconomyMain(plugin);
        new SkyExcelNetworkSkyBlockMain(plugin);
        new SkyExcelSnowyMain(plugin);
        new SkyExcelNetworkTradeMain(plugin);
        new SkyExcelNetWorkWarp(plugin);
        new SkyExcelNetworkTutorialMain(plugin);
    }

    @Override
    public void onDisable() {
//        discord.onDisable();

        this.skyExcelNetworkJobMain.disable();
    }


    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
