package net.skyexcel.server.cashshop.cmd;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class CashShopCmdTab implements TabCompleter {
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();
        if (sender instanceof Player player) {

            Config config = new Config("shop/cash/");
            config.setPlugin(SkyExcelNetworkMain.getPlugin());
            if (args.length == 1) {
                if (player.isOp()) {
                    result = List.of("생성", "제거", "편집", "줄", "제목", "목록", "리로드", "열기");
                } else {
                    result = List.of("열기");
                }
            } else if (args.length == 2) {
                if (List.of("제거", "편집", "제목", "열기").contains(args[0])) {
                    (result).addAll(config.fileListName());
                } else if ("줄".equalsIgnoreCase(args[0])) {
                    result = List.of(String.valueOf(1), String.valueOf(2), String.valueOf(3), String.valueOf(4), String.valueOf(5), String.valueOf(6));
                }
            } else if (args.length == 3 && "줄".equalsIgnoreCase(args[0])) {
                (result).addAll(config.fileListName());
            }
        }
        return result;
    }
}
