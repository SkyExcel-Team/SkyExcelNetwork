package net.skyexcel.server.essentials.playtime.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.essentials.playtime.data.PlayTime;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlayTimeExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public PlayTimeExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "PlayTime";
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
        return "SkyExcelNetwork";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        PlayTime playTime = new PlayTime(player);

        return null; // Placeholder is unknown by the expansion
    }
}
