package net.skyexcel.server.chatchannel.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.skyexcel.api.util.Translate;
import net.skyexcel.server.chatchannel.data.ChatData;
import net.skyexcel.server.skyblock.data.island.rank.Ranking;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ChatExpansion extends PlaceholderExpansion {
    private JavaPlugin plugin; // The instance is created in the constructor and won't be modified, so it can be final

    public ChatExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Type";
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

        ChatData chatData = new ChatData(player);

        return chatData.getChatChannel().getName(); // Placeholder is unknown by the expansion
    }
}
