package net.skyexcel.server.items.cmd;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemsCmdTab implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        if (sender instanceof Player) {
            if (args.length == 1) {
                result = List.of("제작", "제거", "메타", "갯수", "타입");
            } else if (args.length == 2) {
                if (List.of("제작", "제거", "타입").contains(args[0])) {
                    result = List.of("<이름>");
                } else if (args[0].equalsIgnoreCase("메타")) {
                    result = List.of("로어추가", "이름변경", "모델");
                }
            } else if (args.length == 3) {
                if (args[1].equalsIgnoreCase("로어추가")) {
                    result = List.of("<이름>");
                } else if (args[1].equalsIgnoreCase("모델")) {
                    result = List.of("<CustomModelData>");
                } else if (args[0].equalsIgnoreCase("타입")) { //TODO 마인크래프트 아이템 띄우기!
                    for (Material material : Material.values()) {
                        result.add(material.name());
                    }

                }
            }
        }
        return result;
    }
}
