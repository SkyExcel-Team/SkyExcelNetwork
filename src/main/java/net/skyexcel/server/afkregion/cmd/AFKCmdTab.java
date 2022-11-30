package net.skyexcel.server.afkregion.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AFKCmdTab implements TabCompleter {
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList();
        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    result = List.of("확인", "구역", "포인트");
                } else if (args.length == 2) {
                    if ("포인트".equalsIgnoreCase(args[0])) {
                        addPlayer(result);
                    } else {
                        result = List.of("[name]");
                    }

                }
            }
        }

        return result;
    }

    public void addPlayer(List<String> result) {
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        for (OfflinePlayer offlinePlayer : players) {
            result.add(offlinePlayer.getName());
        }
    }
}
