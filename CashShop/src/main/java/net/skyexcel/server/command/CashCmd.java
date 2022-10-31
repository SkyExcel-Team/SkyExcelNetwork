package net.skyexcel.server.command;


import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.Cash;
import net.skyexcel.server.data.StringData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;
import skyexcel.command.tab.Tab;

import java.util.Objects;

public class CashCmd {
    public CashCmd() {
//        Tab<String, String> cashTab = new Tab<>(SkyExcelNetwork.plugin, "캐시");
//
//        Bukkit.getOnlinePlayers().forEach(players -> {
//            cashTab.args("지급", players.getDisplayName(), "<Amount>");
//
//            cashTab.args("빼기", players.getDisplayName(), "<Amount>");
//            cashTab.args("설정", players.getDisplayName(), "<Amount>");
//            cashTab.args("확인", players.getDisplayName());
//            cashTab.args("초기화", players.getDisplayName());
//
//            cashTab.args("정보보기", players.getDisplayName());
//        });
//
//        cashTab.args("모두지급", "<Amount> ");
//        cashTab.args("관리");
//        cashTab.args("리로드");

    }

    public void registerCmd() {
        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "캐시");

        cmd.label(action -> {
            Player player = (Player) action.getSender();
            player.sendMessage(StringData.checkPlayerCash(player));
        });

        cmd.action("지급", 0, action -> {

            try {
                Player player = (Player) action.getSender();
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                int amount = Integer.parseInt(action.getArgs()[2]);

                Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                cash.deposit(amount);

                player.sendMessage(StringData.sendCash(target.getPlayer(), amount));
            } catch (NumberFormatException e) {

            }

        });

        cmd.action("모두지급", 0, action -> {
            try {
                Player player = (Player) action.getSender();

                int amount = Integer.parseInt(action.getArgs()[1]);

                Bukkit.getOnlinePlayers().forEach(players -> {
                    Cash cash = new Cash(Objects.requireNonNull(players));
                    cash.deposit(amount);

                });
                player.sendMessage(StringData.sendCashAllPlayer(amount));
            } catch (NumberFormatException e) {

            }

        });

        cmd.action("빼기", 0, action -> {
            try {
                Player player = (Player) action.getSender();
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                int amount = Integer.parseInt(action.getArgs()[2]);

                Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                cash.withdraw(amount);

                player.sendMessage(StringData.removeCash(target.getPlayer(), amount));
            } catch (NumberFormatException e) {

            }

        });

        cmd.action("설정", 0, action -> {
            try {
                Player player = (Player) action.getSender();
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                int amount = Integer.parseInt(action.getArgs()[2]);

                Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
                cash.Set(amount);

                player.sendMessage(StringData.setCash(target.getPlayer(), amount));
            } catch (NumberFormatException e) {

            }
        });


        cmd.action("확인", 0, action -> {
            Player player = (Player) action.getSender();
            OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);

            player.sendMessage(StringData.checkPlayerCash(target.getPlayer()));
        });

        cmd.action("초기화", 0, action -> {
            Player player = (Player) action.getSender();
            OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
            Cash cash = new Cash(Objects.requireNonNull(target.getPlayer()));
            cash.Set(0);
            player.sendMessage(StringData.checkPlayerCash(target.getPlayer()));
        });

        cmd.action("리로드", 0, action -> {
            Player player = (Player) action.getSender();
            SkyExcelNetwork.message.reloadConfig();
            player.sendMessage(ChatColor.GREEN + "콘피그를 리로드 하였습니다!");
        });
    }
}
