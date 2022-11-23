package net.skyexcel.server.items.cmd;

import net.skyexcel.server.items.data.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemsCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {

            String name;
            Items items;
            switch (args[0]) {
                case "제작" -> {
                    if (args.length > 1) {
                        name = args[1];

                        if (player.hasPermission("items.*")) {

                        }

                    }
                }
                case "제거" -> {
                    if (args.length > 1) {
                        name = args[1];

                        if (player.hasPermission("items.*")) {

                        }
                    }
                }
                case "메타" -> {
                    if (args.length > 1) {
                        if (args[1].equalsIgnoreCase("로어추가")) {

                        } else if (args[1].equalsIgnoreCase("이름변경")) {
                            String rename = args[2];
                            player.sendMessage("아이템 이름을 " + rename + " 로 지정 했습니다.");

                        } else if (args[1].equalsIgnoreCase("모델")) {
                            try {
                                int customModelData = Integer.parseInt(args[2]);
                                player.sendMessage("모델 데이터를 " + customModelData + " 로 지정 했습니다.");

                            } catch (NumberFormatException e) {
                                player.sendMessage("정수를 입력해 주세요!");
                            }
                        }
                    }
                }
                case "갯수" -> {

                }
                case "타입" -> {

                }
            }

        }
        return false;
    }
}
