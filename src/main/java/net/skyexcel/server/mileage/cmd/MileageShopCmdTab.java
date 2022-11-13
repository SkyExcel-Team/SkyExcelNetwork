package net.skyexcel.server.mileage.cmd;

import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.data.file.Config;

import java.util.ArrayList;
import java.util.List;

public class MileageShopCmdTab implements TabCompleter {

    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList();
        if (sender instanceof Player player) {
            if (player.isOp()) {
                Config config = new Config("shop/mileage/");
                config.setPlugin(SkyExcelNetworkMileageMain.plugin);
                if (args.length == 1) {
                    result = List.of("생성", "제거", "편집", "줄", "제목", "목록", "리로드", "가격");
                } else if (args.length == 2) {
                    if (List.of("제거", "편집", "제목").contains(args[0])) {
                        (result).addAll(config.fileListName());
                    } else if ("줄".equalsIgnoreCase(args[0])) {
                        result = List.of(String.valueOf(1), String.valueOf(2), String.valueOf(3), String.valueOf(4), String.valueOf(5), String.valueOf(6));
                    } else if ("가격".equalsIgnoreCase(args[0])) {
                        result = List.of("판매", "구매");
                    }
                } else if (args.length == 3) {
                    if (List.of("줄", "가격").contains(args[0])) {
                        (result).addAll(config.fileListName());
                    }
                } else if (args.length == 4) {
                    if ("가격".equalsIgnoreCase(args[0])) {
                        (result).add("<slot>");
                    }
                } else if (args.length == 5 && "가격".equalsIgnoreCase(args[0])) {
                    (result).add("<amount>");
                }
            }
        }

        return result;
    }
}
