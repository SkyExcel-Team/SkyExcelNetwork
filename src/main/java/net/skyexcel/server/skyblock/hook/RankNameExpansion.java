package net.skyexcel.server.skyblock.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.data.island.rank.Ranking;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RankNameExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public RankNameExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "SName";
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
        return "SkyBlock";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        Ranking ranking = new Ranking();
        return getRankName(params, ranking); // Placeholder is unknown by the expansion
    }

    private String getRankName(String params, Ranking ranking) {

        return switch (params) {
            case "1" -> ranking.getRankName(1);
            case "2" -> ranking.getRankName(2);
            case "3" -> ranking.getRankName(3);
            case "4" -> ranking.getRankName(4);
            case "5" -> ranking.getRankName(5);
            case "6" -> ranking.getRankName(6);
            case "7" -> ranking.getRankName(7);
            case "8" -> ranking.getRankName(8);
            case "9" -> ranking.getRankName(9);
            case "10" -> ranking.getRankName(10);
            default -> "#N/A";
        };
    }
}
