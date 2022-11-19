package net.skyexcel.server;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.skyexcel.server.regionafk.SkyExcelNetworkAFKMain;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.chatchannel.SkyExcelNetworkChatChannelMain;
import net.skyexcel.server.discord.SkyExcelNetworkDiscordMain;
import net.skyexcel.server.essentials.SkyExcelNetworkEssentialsMain;
import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.flyticket.SkyExcelNetworkFlyTicketMain;
import net.skyexcel.server.giftbox.SkyExcelNetworkGiftBoxMain;
import net.skyexcel.server.items.SkyExcelNetworkItemsMain;
import net.skyexcel.server.job.SkyExcelNetworkJobMain;
import net.skyexcel.server.lockmanager.SkyExcelNetworkLockManagerMain;
import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.playerprofile.SkyExcelNetworkPlayerProfile;
import net.skyexcel.server.playtime.SkyExcelNetworkPlayTimeMain;

import net.skyexcel.server.rank.SkyExcelNetworkRankMain;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.snowy.SkyExcelSnowyMain;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import net.skyexcel.server.tutorial.SkyExcelNetworkTutorialMain;
import net.skyexcel.server.upgrade.SkyExcelNetworkUpgradeMain;
import net.skyexcel.server.warp.SkyExcelNetWorkWarp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkMain extends JavaPlugin {
    private static JavaPlugin plugin;
    public static HeadDatabaseAPI hdb;

    private volatile SkyExcelNetworkDiscordMain discord = null;
    private SkyExcelNetworkJobMain skyExcelNetworkJobMain;
    private SkyExcelSnowyMain skyExcelNetworkSnowyMain;
    public static WorldEditPlugin WorldEdit;


    @Override
    public void onEnable() {
        plugin = this;

        hdb = new HeadDatabaseAPI();

        new SkyExcelNetworkChatChannelMain(plugin);
        new SkyExcelNetworkCashShopMain(plugin);
        discord = new SkyExcelNetworkDiscordMain(plugin);
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
        this.skyExcelNetworkSnowyMain = new SkyExcelSnowyMain(plugin);
        new SkyExcelNetworkTradeMain(plugin);
        new SkyExcelNetWorkWarp(plugin);
        new SkyExcelNetworkFlyTicketMain(plugin);
        new SkyExcelNetworkTutorialMain(plugin);
        new SkyExcelNetworkEssentialsMain(plugin);
        new SkyExcelNetworkAFKMain(plugin);
        new SkyExcelNetworkRankMain(plugin);
        new SkyExcelNetworkUpgradeMain(plugin);

        if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null) {
            WorldEdit = getWorldEditPlugin();
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " WorldEdit 플러그인이 존재하지 않습니다. 일부 기능이 작동 하지 않을 수 있습니다.");
        }
    }

    @Override
    public void onDisable() {
        discord.onDisable();

        this.skyExcelNetworkSnowyMain.disable();
        this.skyExcelNetworkJobMain.disable();
    }

    public static WorldEditPlugin getWorldEditPlugin() {
        Plugin worldedit = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (worldedit instanceof WorldEditPlugin) return (WorldEditPlugin) worldedit;
        else return null;
    }
    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
