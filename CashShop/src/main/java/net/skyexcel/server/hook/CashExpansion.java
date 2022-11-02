package net.skyexcel.server.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.Cash;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CashExpansion extends PlaceholderExpansion {
    private SkyExcelNetwork plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public CashExpansion(SkyExcelNetwork plugin) {
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
        return (plugin = (SkyExcelNetwork) Bukkit.getPluginManager().getPlugin(Objects.requireNonNull(getRequiredPlugin()))) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        Cash cash = new Cash(player);
        if (params.equalsIgnoreCase("Cash")) {
            return String.valueOf(cash.getAmount());
        } else if (params.equalsIgnoreCase("Cash_fixed")) {
            return Translate.decal(cash.getAmount());
        }
        return null; // Placeholder is unknown by the expansion
    }
}
