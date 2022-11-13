package net.skyexcel.server;

import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.chatchannel.SkyExcelNetworkChatChannelMain;
import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.giftbox.SkyExcelNetworkGiftBoxMain;
import net.skyexcel.server.items.SkyExcelNetworkItemsMain;
import net.skyexcel.server.lockmanager.SkyExcelNetworkLock;
import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.playerprofile.SkyExcelNetworkPlayerProfile;
import net.skyexcel.server.playtime.SkyExcelNetworkPlayTimeMain;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEConomyMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import net.skyexcel.server.warp.SkyExcelNetWorkWarp;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkMain extends JavaPlugin {
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        new SkyExcelNetworkChatChannelMain(plugin);
        new SkyExcelNetworkFishMain(plugin);
        new SkyExcelNetworkGiftBoxMain(plugin);
        new SkyExcelNetworkItemsMain(plugin);
        new SkyExcelNetworkMenuMain(plugin);
        new SkyExcelNetworkCashShopMain(plugin);
        new SkyExcelNetworkMileageMain(plugin);
        new SkyExcelNetworkPlayTimeMain(plugin);
        new SkyExcelNetworkSEConomyMain(plugin);
        new SkyExcelNetworkSkyBlockMain(plugin);
        new SkyExcelNetworkTradeMain(plugin);
        new SkyExcelNetworkLock(plugin);
        new SkyExcelNetWorkWarp(plugin);
        new SkyExcelNetworkPlayerProfile(plugin);
    }

    @Override
    public void onDisable() {
    }


    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
