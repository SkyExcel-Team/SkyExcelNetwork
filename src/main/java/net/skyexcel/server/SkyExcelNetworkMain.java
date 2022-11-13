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
import net.skyexcel.server.seconomy.SkyExcelNetworkSEConomyMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import net.skyexcel.server.tutorial.SkyExcelNetworkTutorialMain;
import net.skyexcel.server.warp.SkyExcelNetWorkWarp;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkMain extends JavaPlugin {
    private static JavaPlugin plugin;
    public static HeadDatabaseAPI hdb;
    private SkyExcelNetworkDiscordMain discord;

    @Override
    public void onEnable() {
        hdb = new HeadDatabaseAPI();

        new SkyExcelNetworkChatChannelMain(plugin);
        new SkyExcelNetworkCashShopMain(plugin);
        discord = new SkyExcelNetworkDiscordMain();
        new SkyExcelNetworkFishMain(plugin);
        new SkyExcelNetworkGiftBoxMain(plugin);
        new SkyExcelNetworkItemsMain(plugin);
        new SkyExcelNetworkJobMain(plugin);
        new SkyExcelNetworkLockManagerMain(plugin);
        new SkyExcelNetworkMenuMain(plugin);
        new SkyExcelNetworkMileageMain(plugin);
        new SkyExcelNetworkPlayerProfile(plugin);
        new SkyExcelNetworkPlayTimeMain(plugin);
        new SkyExcelNetworkSEConomyMain(plugin);
        new SkyExcelNetworkSkyBlockMain(plugin);
        new SkyExcelNetworkTradeMain(plugin);
        new SkyExcelNetWorkWarp(plugin);
        new SkyExcelNetworkTutorialMain(plugin);
    }

    @Override
    public void onDisable() {
        discord.onDisable();
    }

    @Override
    public void onLoad() {
        plugin = this;

        discord.onLoad(plugin);
    }


    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
