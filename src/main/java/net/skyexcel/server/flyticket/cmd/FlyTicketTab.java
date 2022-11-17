package net.skyexcel.server.flyticket.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlyTicketTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();
        List<String> contains = List.of("추가", "제거", "모두지급");


        if (sender instanceof Player player) {
            if (args.length == 1) {
                result = List.of("시간", "월드", "생성");
            } else if (args.length == 2) {
                if ("시간".equalsIgnoreCase(args[0])) {
                    result = List.of("추가", "제거", "확인", "모두지급");
                }
            } else if (args.length == 3) {
                if (List.of("추가", "제거", "확인").contains(args[1])) {
                    for (OfflinePlayer name : Bukkit.getOfflinePlayers()) {
                        result.add(name.getName());
                    }
                } else if ("모두지급".equalsIgnoreCase(args[1])) {
                    if (args.length == 3) {
                        if (contains.contains(args[1])) {
                            result.add("년");
                        }
                    } else if (args.length == 4) {
                        if (contains.contains(args[1])) {
                            result.add("달");
                        }
                    } else if (args.length == 5) {
                        if (contains.contains(args[1])) {
                            result.add("일");
                        }
                    } else if (args.length == 6) {
                        if (contains.contains(args[1])) {
                            result.add("시간");
                        }
                    } else if (args.length == 7) {
                        if (contains.contains(args[1])) {
                            result.add("분");
                        }
                    } else if (args.length == 8) {
                        if (contains.contains(args[1])) {
                            result.add("초");
                        }
                    }
                }
            } else if (List.of("추가", "제거", "확인").contains(args[1]))
                if (args.length == 4) {
                    if (contains.contains(args[1])) {
                        result.add("년");
                    }
                } else if (args.length == 5) {
                    if (contains.contains(args[1])) {
                        result.add("달");
                    }
                } else if (args.length == 6) {
                    if (contains.contains(args[1])) {
                        result.add("일");
                    }
                } else if (args.length == 7) {
                    if (contains.contains(args[1])) {
                        result.add("시간");
                    }
                } else if (args.length == 8) {
                    if (contains.contains(args[1])) {
                        result.add("분");
                    }
                } else if (args.length == 9) {
                    if (contains.contains(args[1])) {
                        result.add("초");
                    }
                }
        }

        return result;
    }
}
