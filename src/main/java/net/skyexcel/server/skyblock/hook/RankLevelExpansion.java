package net.skyexcel.server.skyblock.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.skyblock.util.Translate;
import net.skyexcel.server.skyblock.SkyExcelNetworkSkyBlockMain;
import net.skyexcel.server.skyblock.data.island.rank.Ranking;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RankLevelExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public RankLevelExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "SLevel";
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


        return getRankLevel(params, ranking); // Placeholder is unknown by the expansion
    }

    private String getRankLevel(String params, Ranking ranking) {


        return switch (params) {
            case "1" -> Translate.decal(ranking.getRankLevel(1));
            case "2" -> Translate.decal(ranking.getRankLevel(2));
            case "3" -> Translate.decal(ranking.getRankLevel(3));
            case "4" -> Translate.decal(ranking.getRankLevel(4));
            case "5" -> Translate.decal(ranking.getRankLevel(5));
            case "6" -> Translate.decal(ranking.getRankLevel(6));
            case "7" -> Translate.decal(ranking.getRankLevel(7));
            case "8" -> Translate.decal(ranking.getRankLevel(8));
            case "9" -> Translate.decal(ranking.getRankLevel(9));
            case "10" -> Translate.decal(ranking.getRankLevel(10));
            default -> "#N/A";
        };
    }
}
