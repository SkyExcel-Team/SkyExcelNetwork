package net.skyexcel.server.flyticket.cmd;

import net.skyexcel.server.flyticket.data.FlyTime;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class FlyTicketCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player player) {
            if (args.length > 1) {
                switch (args[0]) {
                    case "시간" -> {
                        if (args.length > 2) {
                            switch (args[1]) {
                                case "추가" -> {

                                }
                                case "제거" -> {

                                }
                                case "모두지급" -> {
                                    if (args.length > 3) {
                                        if (args.length > 4) {
                                            if (args.length > 5) {
                                                if (args.length > 6) {
                                                    if (args.length > 7) {

                                                        try {
                                                            int Year = Integer.parseInt(args[2]);

                                                            int Month = Integer.parseInt(args[3]);
                                                            int Day = Integer.parseInt(args[4]);
                                                            int Hour = Integer.parseInt(args[5]);
                                                            int Minute = Integer.parseInt(args[6]);
                                                            int Second = Integer.parseInt(args[7]);

                                                            Bukkit.getOnlinePlayers().forEach(players -> {
                                                                FlyTime flyTime = new FlyTime(players);
                                                                flyTime.addTimeAsYear(Year, Month, Day, Hour, Minute, Second);
                                                            });
                                                            player.sendMessage("모두에게 " + Year + "년 " + Month + "달 " + Day + "일 " + Hour + "시간 " + Minute + "분 " + Second + "초" + " 시간을 지급 하였습니다.");
                                                        } catch (NumberFormatException e) {
                                                            player.sendMessage("정수를 입력 해 주세요!");
                                                        }

                                                    } else {
                                                        player.sendMessage("초를 입력해 주세요!");
                                                    }
                                                } else {
                                                    player.sendMessage("시간을 입력해 주세요!");
                                                }
                                            } else {
                                                player.sendMessage("일을 입력해 주세요!");
                                            }
                                        } else {
                                            player.sendMessage("달을 입력해 주세요!");
                                        }
                                    } else {
                                        player.sendMessage("년을 입력해 주세요!");
                                    }
                                }
                                case "확인" -> {
                                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
                                    FlyTime flyTime = new FlyTime(target);
                                    player.sendMessage(target.getName() + " 님의 시간 : ㅁㄴㅇ리ㅏㅓㅁㄴㅇ리ㅏㅓ;");
                                }

                            }
                        }
                    }
                    case "월드" -> {

                    }
                    case "생성" -> {

                    }
                }
            }
        }


        return false;
    }
}
