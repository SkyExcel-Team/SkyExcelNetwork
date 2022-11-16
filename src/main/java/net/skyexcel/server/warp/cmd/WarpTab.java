package net.skyexcel.server.warp.cmd;

import net.skyexcel.server.warp.data.Warp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        if (sender instanceof Player player) {
            if (args.length == 1) {
                Warp warp = new Warp();
                result = warp.getList();

                if (player.isOp()) {
                    if (!warp.getList().isEmpty())
                        result = warp.getList();

                    result.add("생성");
                    result.add("삭제");
                }
            } else if (args.length == 2) {
                if (Objects.equals("삭제", args[0])) {
                    Warp warp = new Warp();
                    result = warp.getList();
                } else {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        result.add(online.getDisplayName());
                    }
                }
            }
        }
        return result;
    }
}
