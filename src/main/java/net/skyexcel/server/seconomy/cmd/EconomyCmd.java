package net.skyexcel.server.seconomy.cmd;


import net.skyexcel.server.seconomy.SkyExcelNetworkSEconomyMain;
import net.skyexcel.server.seconomy.data.SEConomy;
import net.skyexcel.server.seconomy.data.StringData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skyexcel.command.function.Cmd;

import java.util.Objects;

public class EconomyCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        StringData stringData = new StringData();

        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(stringData.mymoneny(player));
            } else {
                switch (args[0]) {
                    case "지급" -> {
                        if (player.isOp()) {
                            try {

                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                long amount = Long.parseLong(args[2]);

                                SEConomy cash = new SEConomy(Objects.requireNonNull(target.getPlayer()));
                                cash.deposit(amount);

                                player.sendMessage(stringData.sendmoneny(target.getPlayer(), amount));
                            } catch (NumberFormatException e) {

                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
                        }
                    }

                    case "모두지급" -> {
                        if (player.isOp()) {
                            try {
                                long amount = Long.parseLong(args[1]);

                                Bukkit.getOnlinePlayers().forEach(players -> {
                                    SEConomy cash = new SEConomy(Objects.requireNonNull(players));
                                    cash.deposit(amount);

                                });
                                player.sendMessage(stringData.sendmonenyAllPlayer(amount));
                            } catch (NumberFormatException e) {

                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
                        }
                    }
                    case "빼기" -> {
                        if (player.isOp()) {
                            try {

                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                long amount = Long.parseLong(args[2]);

                                SEConomy cash = new SEConomy(Objects.requireNonNull(target.getPlayer()));
                                cash.withdraw(amount);

                                player.sendMessage(stringData.removemoneny(target.getPlayer(), amount));
                            } catch (NumberFormatException e) {

                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
                        }
                    }
                    case "설정" -> {
                        if (player.isOp()) {
                            try {

                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                long amount = Long.parseLong(args[2]);

                                SEConomy cash = new SEConomy(Objects.requireNonNull(target.getPlayer()));
                                cash.setAmount(amount);

                                player.sendMessage(stringData.setmoneny(target.getPlayer(), amount));
                            } catch (NumberFormatException e) {

                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
                        }
                    }
                    case "확인" -> {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                        player.sendMessage(stringData.checkPlayermoneny(target.getPlayer()));
                    }
                    case "초기화" -> {
                        if (player.isOp()) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            SEConomy cash = new SEConomy(Objects.requireNonNull(target.getPlayer()));
                            cash.setAmount(0);
                            player.sendMessage(stringData.resetmoneny(target.getPlayer()));
                        } else {
                            player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
                        }
                    }
                    case "리로드" -> {
                        if (player.isOp()) {
                            SkyExcelNetworkSEconomyMain.message.reloadConfig();
                            player.sendMessage(ChatColor.GREEN + "콘피그를 리로드 하였습니다!");
                        } else {
                            player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
                        }
                    }
                }
            }
        }


        return false;
    }


}
