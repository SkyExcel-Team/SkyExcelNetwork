package net.skyexcel.server.seconomy.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.seconomy.SkyExcelNetworkSEConomyMain;
import net.skyexcel.server.data.economy.SEconomy;
import net.skyexcel.server.seconomy.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SEConomyExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public SEConomyExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "SEconomy";
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
        return "SEconomy";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("money_fixed")) {
            SEconomy economy = new SEconomy(player);
            return Translate.decal(economy.getMoney());
        } else if (params.equalsIgnoreCase("money")) {
            SEconomy economy = new SEconomy(player);
            return String.valueOf(economy.getMoney());
        }
        return null; // Placeholder is unknown by the expansion
    }
}
