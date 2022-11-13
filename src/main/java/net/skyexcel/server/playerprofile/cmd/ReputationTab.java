package net.skyexcel.server.playerprofile.cmd;

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

public class ReputationTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();


        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (player.isOp()) {
                    result = List.of("올리기", "내리기", "설정", "초기화", "보기");
                } else {
                    result = List.of("올리기", "내리기", "보기");
                }

            } else if (args.length == 2) {
                if (player.isOp()) {
                    if (List.of("설정", "초기화", "올리기", "내리기", "보기").contains(args[0])) {
                        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                            result.add(offlinePlayer.getName());
                        }
                    }
                } else {
                    if (List.of("올리기", "내리기", "보기").contains(args[0])) {
                        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                            result.add(offlinePlayer.getName());
                        }
                    }
                }
            } else if (args.length == 3) {
                if (player.isOp()) {
                    if ("설정".equalsIgnoreCase(args[0])) {
                        result = List.of("[값]");
                    }
                }
            }
        }

        return result;
    }
}
