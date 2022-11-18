package net.skyexcel.server.job.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class JobTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            result = List.of("보기", "초기화", "선택");
        } else if (args.length == 2) {
            if (List.of("보기", "초기화").contains(args[0])) {
                for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                    result.add(offlinePlayer.getName());
                }
            }

        }
        return result;
    }
}
