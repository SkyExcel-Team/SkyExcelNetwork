package net.skyexcel.server.skyblock.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.api.util.Translate;
import net.skyexcel.server.skyblock.data.island.rank.Ranking;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class RankExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public RankExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "SRank";
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
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin((getRequiredPlugin()))) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        Ranking ranking = new Ranking();


        return getRankLevel(params, ranking); // Placeholder is unknown by the expansion
    }

    private String getRankLevel(String params, Ranking ranking) {
        Translate translate = new Translate();
        return switch (Integer.parseInt(params)) {
            case 1 -> translate.decal(ranking.getRank(1));
            case 2 -> translate.decal(ranking.getRank(2));
            case 3 -> translate.decal(ranking.getRank(3));
            case 4 -> translate.decal(ranking.getRank(4));
            case 5 -> translate.decal(ranking.getRank(5));
            case 6 -> translate.decal(ranking.getRank(6));
            case 7 -> translate.decal(ranking.getRank(7));
            case 8 -> translate.decal(ranking.getRank(8));
            case 9 -> translate.decal(ranking.getRank(9));
            case 10 -> translate.decal(ranking.getRank(10));
            default -> "#N/A";
        };
    }
}
