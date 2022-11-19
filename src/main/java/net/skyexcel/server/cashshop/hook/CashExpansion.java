package net.skyexcel.server.cashshop.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.api.util.Translate;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.data.Cash;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CashExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public CashExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Cash";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SkyExcel";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }


    @Override
    public String getRequiredPlugin() {
        return "CashShop";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        Cash cash = new Cash(player);
        Translate translate = new Translate();
        if (params.equalsIgnoreCase("Cash")) {
            return String.valueOf(cash.getLong());
        } else if (params.equalsIgnoreCase("Cash_fixed")) {
            return translate.decal(cash.getLong());
        }
        return null; // Placeholder is unknown by the expansion
    }
}
