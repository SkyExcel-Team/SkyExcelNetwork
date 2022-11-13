package net.skyexcel.server.playerprofile.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlayerProfileTab implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        if (sender instanceof Player) {
            if (args.length == 1) {
                result.add("열기");
            } else if (args.length == 2) {
                for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                    result.add(offlinePlayer.getName());
                }
            }
        }

        return result;
    }
}
