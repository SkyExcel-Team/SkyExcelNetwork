package net.skyexcel.server.skyblock.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.api.util.Translate;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import net.skyexcel.server.skyblock.data.island.vault.SkyBlockVault;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SkyBlockVaultExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public SkyBlockVaultExpansion(JavaPlugin plugin) {
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
        return (plugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("vault_fixed")) {
            SkyBlockPlayerData playerData = new SkyBlockPlayerData(Objects.requireNonNull(player.getPlayer()));
            SkyBlockVault vault = new SkyBlockVault(player.getPlayer(), playerData.getIsland());
            Translate translate = new Translate();
            return translate.decal(vault.getAmount());
        } else if (params.equalsIgnoreCase("vault")) {

            SkyBlockPlayerData playerData = new SkyBlockPlayerData(Objects.requireNonNull(player.getPlayer()));
            SkyBlockVault vault = new SkyBlockVault(player.getPlayer(), playerData.getIsland());
            return String.valueOf(vault.getAmount());
        }
        return null; // Placeholder is unknown by the expansion
    }
}
