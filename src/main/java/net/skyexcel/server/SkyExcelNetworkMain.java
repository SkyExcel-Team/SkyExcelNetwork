package net.skyexcel.server;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.skyexcel.server.essentials.events.PluginDisableEvent;
import net.skyexcel.server.essentials.events.PluginEnableEvent;
import net.skyexcel.server.glow.SkyExcelNetworkGlowMain;
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
import net.skyexcel.server.playerprofile.SkyExcelNetworkPlayerProfileMain;
import net.skyexcel.server.playtime.SkyExcelNetworkPlayTimeMain;

import net.skyexcel.server.quest.SkyExcelNetworkDailyQuestMain;
import net.skyexcel.server.rank.SkyExcelNetworkRankMain;
import net.skyexcel.server.regionafk.SkyExcelNetworkRegionAFKMain;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.snowy.SkyExcelSnowyMain;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import net.skyexcel.server.tutorial.SkyExcelNetworkTutorialMain;
import net.skyexcel.server.upgrade.SkyExcelNetworkUpgradeMain;
import net.skyexcel.server.warp.SkyExcelNetWorkWarpMain;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SkyExcelNetworkMain extends JavaPlugin {
    private static JavaPlugin plugin;
    public static HeadDatabaseAPI hdb;
    public static WorldEditPlugin WorldEdit;


    @Override
    public void onEnable() {
        plugin = this;

        //Load HeadDatabaseAPI
        hdb = new HeadDatabaseAPI();

        //Register Listeners
        Listener[] listeners = {new SkyExcelNetworkCashShopMain(), new SkyExcelNetworkChatChannelMain(), new SkyExcelNetworkDiscordMain(), new SkyExcelNetworkEssentialsMain(), new SkyExcelNetworkFishMain(),
                new SkyExcelNetworkFlyTicketMain(), new SkyExcelNetworkGiftBoxMain(), new SkyExcelNetworkGlowMain(), new SkyExcelNetworkItemsMain(), new SkyExcelNetworkJobMain(),
                new SkyExcelNetworkLockManagerMain(), new SkyExcelNetworkMenuMain(), new SkyExcelNetworkMileageMain(), new SkyExcelNetworkPlayerProfileMain(), new SkyExcelNetworkPlayTimeMain(),
                new SkyExcelNetworkRankMain(), new SkyExcelNetworkRegionAFKMain(), new SkyExcelNetworkSEconomyMain(), new SkyExcelNetworkSkyBlockMain(), new SkyExcelSnowyMain(),
                new SkyExcelNetworkTradeMain(), new SkyExcelNetworkTutorialMain(), new SkyExcelNetworkUpgradeMain(), new SkyExcelNetWorkWarpMain(), new SkyExcelNetworkDailyQuestMain()};

        Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));

        //Call Event
        Bukkit.getPluginManager().callEvent(new PluginEnableEvent(plugin));

        //WorldEdit
        if (Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") != null)
            WorldEdit = getWorldEditPlugin();
        else
            Bukkit.getLogger().warning("WorldEdit 플러그인이 존재하지 않습니다. 일부 기능이 작동 하지 않을 수 있습니다.");
    }

    @Override
    public void onDisable() {
        Bukkit.getPluginManager().callEvent(new PluginDisableEvent());
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
