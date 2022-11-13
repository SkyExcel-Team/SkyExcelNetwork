package net.skyexcel.server.warp.cmd;

import net.skyexcel.server.warp.data.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WarpTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (player.isOp()) {
                    result = List.of("생성", "이동", "삭제");
                } else {
                    result = List.of("이동");
                }
            } else if (args.length == 2) {
                if (List.of("생성", "이동", "삭제").contains(args[0])) {
                    Warp warp = new Warp();
                    result = warp.getList();
                }
            }
        }
        return result;
    }
}
