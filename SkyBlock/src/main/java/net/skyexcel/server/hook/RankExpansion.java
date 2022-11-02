package net.skyexcel.server.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.rank.Ranking;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RankExpansion extends PlaceholderExpansion {
    private SkyBlockCore plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public RankExpansion(SkyBlockCore plugin) {
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
        return "SkyBlock";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (SkyBlockCore) Bukkit.getPluginManager().getPlugin(Objects.requireNonNull(getRequiredPlugin()))) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        Ranking ranking = new Ranking();


        return getRankLevel(params, ranking); // Placeholder is unknown by the expansion
    }

    private String getRankLevel(String params, Ranking ranking) {

        return switch (Integer.parseInt(params)) {
            case 1 -> Translate.decal(ranking.getRank(1));
            case 2 -> Translate.decal(ranking.getRank(2));
            case 3 -> Translate.decal(ranking.getRank(3));
            case 4 -> Translate.decal(ranking.getRank(4));
            case 5 -> Translate.decal(ranking.getRank(5));
            case 6 -> Translate.decal(ranking.getRank(6));
            case 7 -> Translate.decal(ranking.getRank(7));
            case 8 -> Translate.decal(ranking.getRank(8));
            case 9 -> Translate.decal(ranking.getRank(9));
            case 10 -> Translate.decal(ranking.getRank(10));
            default -> "#N/A";
        };
    }
}
