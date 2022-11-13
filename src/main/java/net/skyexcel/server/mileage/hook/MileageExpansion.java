package net.skyexcel.server.mileage.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;

import net.skyexcel.server.mileage.data.Mileage;
import net.skyexcel.server.mileage.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MileageExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public MileageExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Mileage";
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
        return "SMileage";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("mileage_fixed")) {
            Mileage economy = new Mileage(player);
            return Translate.decal(economy.getLong());
        } else if (params.equalsIgnoreCase("mileage")) {
            Mileage economy = new Mileage(player);
            return String.valueOf(economy.getLong());
        }
        return null; // Placeholder is unknown by the expansion
    }
}
