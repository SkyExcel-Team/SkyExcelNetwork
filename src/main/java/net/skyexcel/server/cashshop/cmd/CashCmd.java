package net.skyexcel.server.cashshop.cmd;


import net.skyexcel.server.cashshop.SkyExcelNetworkCashShopMain;
import net.skyexcel.server.cashshop.data.Cash;
import net.skyexcel.server.cashshop.data.StringData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

import java.util.Objects;

public class CashCmd {

    public void registerCmd() {
        Cmd cmd = new Cmd(SkyExcelNetworkCashShopMain.plugin, "캐시");
        StringData stringData = new StringData();

        cmd.label(action -> {
            Player player = (Player) action.getSender();
            player.sendMessage(stringData.myCash(player));
        });

        cmd.action("지급", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                try {

                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                    long amount = Long.parseLong(action.getArgs()[2]);

                    Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                    cash.deposit(amount);

                    player.sendMessage(stringData.sendCash(target.getPlayer(), amount));
                } catch (NumberFormatException e) {

                }
            } else {
                player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
            }


        });

        cmd.action("모두지급", 0, action -> {
            Player player = (Player) action.getSender();

            if (player.isOp()) {
                try {


                    long amount = Long.parseLong(action.getArgs()[1]);

                    Bukkit.getOnlinePlayers().forEach(players -> {
                        Cash cash = new Cash(Objects.requireNonNull(players));
                        cash.deposit(amount);

                    });
                    player.sendMessage(stringData.sendCashAllPlayer(amount));
                } catch (NumberFormatException e) {

                }
            } else {
                player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
            }


        });

        cmd.action("빼기", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                try {

                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                    long amount = Long.parseLong(action.getArgs()[2]);

                    Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                    cash.withdraw(amount);

                    player.sendMessage(stringData.removeCash(target.getPlayer(), amount));
                } catch (NumberFormatException e) {

                }
            } else {
                player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
            }


        });

        cmd.action("설정", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                try {

                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                    long amount = Long.parseLong(action.getArgs()[2]);

                    Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                    cash.setAmount(amount);

                    player.sendMessage(stringData.setCash(target.getPlayer(), amount));
                } catch (NumberFormatException e) {

                }
            } else {
                player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
            }


        });


        cmd.action("확인", 0, action -> {
            Player player = (Player) action.getSender();

            OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);

            player.sendMessage(stringData.checkPlayerCash(target.getPlayer()));
        });

        cmd.action("초기화", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                cash.setAmount(0);
                player.sendMessage(stringData.resetCash(target.getPlayer()));
            } else {
                player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
            }

        });

        cmd.action("리로드", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.isOp()) {
                SkyExcelNetworkCashShopMain.message.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "콘피그를 리로드 하였습니다!");
            } else {
                player.sendMessage(ChatColor.RED + "당신은 관리자가 아닙니다.");
            }

        });
    }

}
