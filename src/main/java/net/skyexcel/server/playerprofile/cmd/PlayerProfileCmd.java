package net.skyexcel.server.playerprofile.cmd;

import net.skyexcel.server.playerprofile.data.PlayerProfile;
import net.skyexcel.server.playerprofile.data.PlayerProfileData;
import net.skyexcel.server.playerprofile.data.PlayerProfileGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerProfileCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {

                if ("열기".equalsIgnoreCase(args[0])) {
                    if (args.length > 1) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                        PlayerProfile playerProfile = new PlayerProfile(target);
                        PlayerProfileGUI playerProfileGUI = new PlayerProfileGUI(target);
                        playerProfileGUI.onGUI(player);
                        PlayerProfileData.playerProfileGUIHashMap.put(player.getUniqueId(), playerProfileGUI);
                    }
                }
            }
        }
        return false;
    }
}
