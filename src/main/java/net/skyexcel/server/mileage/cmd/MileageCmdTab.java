package net.skyexcel.server.mileage.cmd;

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

public class MileageCmdTab implements TabCompleter {
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList();
        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    result = List.of("지급", "모두지급", "빼기", "설정", "확인", "초기화");
                } else if (args.length == 2) {
                    if (!"모두지급".equalsIgnoreCase(args[0])) {
                        this.addPlayer((List)result);
                    }
                } else if (args.length == 3 && !List.of("확인", "초기화").contains(args[0])) {
                    result = List.of("<Amount>");
                }
            }
        }

        return (List)result;
    }

    public void addPlayer(List<String> result) {
        OfflinePlayer[] var2 = Bukkit.getOfflinePlayers();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            OfflinePlayer offlinePlayer = var2[var4];
            result.add(offlinePlayer.getName());
        }

    }
}
