package net.skyexcel.server;

import net.skyexcel.server.menu.SkyExcelNetworkMenuMain;
import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.playtime.SkyExcelNetworkPlayTimeMain;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.trade.SkyExcelNetworkTradeMain;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyExcelNetworkMain extends JavaPlugin {
    private JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        new SkyExcelNetworkMenuMain(plugin);
        new SkyExcelNetworkMileageMain(plugin);
        new SkyExcelNetworkPlayTimeMain(plugin);
        new SkyExcelNetworkSkyBlockMain(plugin);
        new SkyExcelNetworkTradeMain(plugin);
    }

    @Override
    public void onDisable() {}
}
