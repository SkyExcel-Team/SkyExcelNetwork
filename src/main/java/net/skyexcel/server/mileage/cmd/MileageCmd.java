package net.skyexcel.server.mileage.cmd;

import net.skyexcel.server.mileage.SkyExcelNetworkMileageMain;
import net.skyexcel.server.mileage.data.Mileage;
import net.skyexcel.server.mileage.data.StringData;
import net.skyexcel.server.mileage.data.economy.MileageRecord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

import java.util.Objects;

public class MileageCmd {
    public void registerCmd() {
        MileageRecord record = new MileageRecord();
        Cmd cmd = new Cmd(SkyExcelNetworkMileageMain.plugin, "마일리지");
        cmd.label((action) -> {
            Player player = (Player) action.getSender();
            player.sendMessage(StringData.checkPlayerCash(player));
        });
        cmd.action("지급", 0, (action) -> {
            Player player = (Player) action.getSender();

            try {
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                int amount = Integer.parseInt(action.getArgs()[2]);
                Mileage cash = new Mileage((OfflinePlayer) Objects.requireNonNull(target));
                record.setBefore(cash.getLong());
                cash.deposit((long) amount);
                record.setAfter(cash.getLong());
                record.playerRecord(player, target, (long) amount, MileageRecord.Type.ADD);
                player.sendMessage(StringData.sendCash(target, amount));
            } catch (NumberFormatException var6) {
                player.sendMessage("정수를 입력해 주세요.");
            }

        });
        cmd.action("모두지급", 0, (action) -> {
            Player player = (Player) action.getSender();

            try {
                int amount = Integer.parseInt(action.getArgs()[1]);
                Bukkit.getOnlinePlayers().forEach((players) -> {
                    Mileage cash = new Mileage((OfflinePlayer) Objects.requireNonNull(players));
                    record.setBefore(cash.getLong());
                    cash.deposit((long) amount);
                    record.setAfter(cash.getLong());
                    record.playerRecord(player, players, (long) amount, MileageRecord.Type.ADD);
                });
                player.sendMessage(StringData.sendCashAllPlayer(amount));
            } catch (NumberFormatException var4) {
                player.sendMessage("정수를 입력해 주세요.");
            }

        });
        cmd.action("빼기", 0, (action) -> {
            Player player = (Player) action.getSender();

            try {
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                int amount = Integer.parseInt(action.getArgs()[2]);
                Mileage cash = new Mileage((OfflinePlayer) Objects.requireNonNull(target.getPlayer()));
                record.setBefore(cash.getLong());
                cash.withdraw((long) amount);
                record.setAfter(cash.getLong());
                record.playerRecord(player, target, (long) amount, MileageRecord.Type.REMOVE);
                player.sendMessage(StringData.removeCash(target, amount));
            } catch (NumberFormatException var6) {
                player.sendMessage("정수를 입력해 주세요.");
            }

        });
        cmd.action("설정", 0, (action) -> {
            Player player = (Player) action.getSender();

            try {
                OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                int amount = Integer.parseInt(action.getArgs()[2]);
                Mileage cash = new Mileage(target);
                record.setBefore(cash.getLong());
                cash.setAmount((long) amount);
                record.setAfter(cash.getLong());
                record.playerRecord(player, target, (long) amount, MileageRecord.Type.SET);
                player.sendMessage(StringData.setCash(target, amount));
            } catch (NumberFormatException var6) {
                player.sendMessage("정수를 입력해 주세요.");
            }

        });
        cmd.action("확인", 0, (action) -> {
            Player player = (Player) action.getSender();
            OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
            player.sendMessage(StringData.checkPlayerCash(target));
        });
        cmd.action("초기화", 0, (action) -> {
            Player player = (Player) action.getSender();
            OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
            Mileage cash = new Mileage(Objects.requireNonNull(target));
            record.setBefore(cash.getLong());
            cash.setAmount(0L);
            record.setAfter(cash.getLong());
            record.playerRecord(player, target, 0L, MileageRecord.Type.RESET);
            player.sendMessage(StringData.checkPlayerCash(target));
        });
        cmd.action("리로드", 0, (action) -> {
            Player player = (Player) action.getSender();
            SkyExcelNetworkMileageMain.message.reloadConfig();
            player.sendMessage(ChatColor.GREEN + "콘피그를 리로드 하였습니다!");
        });
    }
}
