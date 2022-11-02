package net.skyexcel.server.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.server.SkyBlockCore;
import net.skyexcel.server.data.player.SkyBlockPlayerData;
import net.skyexcel.server.data.vault.SkyBlockVault;
import net.skyexcel.server.util.Translate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SkyBlockVaultExpansion extends PlaceholderExpansion {
    private SkyBlockCore plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public SkyBlockVaultExpansion(SkyBlockCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "SkyBlock";
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
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("vault_fixed")) {
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(Objects.requireNonNull(player.getPlayer()));
            SkyBlockVault vault = new SkyBlockVault(player.getPlayer(), playerData.getIsland());
            return Translate.decal(vault.getAmount());
        } else if (params.equalsIgnoreCase("vault")) {

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(Objects.requireNonNull(player.getPlayer()));
            SkyBlockVault vault = new SkyBlockVault(player.getPlayer(), playerData.getIsland());
            return String.valueOf(vault.getAmount());
        }
        return null; // Placeholder is unknown by the expansion
    }
}
