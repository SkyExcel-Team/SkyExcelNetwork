package net.skyexcel.server.cmd;

import net.skyexcel.server.SkyExcelNetwork;
import net.skyexcel.server.data.StringData;
import net.skyexcel.server.data.economy.Mileage;
import net.skyexcel.server.data.economy.MileageRecord;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import skyexcel.command.function.Cmd;

public class MileageCommand {
    public MileageCommand() {
        Cmd cmd = new Cmd(SkyExcelNetwork.plugin, "마일리지");

        cmd.label(action -> {
            Player player = (Player) action.getSender();
            StringData.myMoney(player);
        });
        
        cmd.action("도움말", 0, action -> {
            Player player = (Player) action.getSender();
            if (player.hasPermission("") || player.isOp()) {
                StringData.command_guide_economy(player);
            } else {
                StringData.command_economy(player);
            }
        });

        cmd.action("모두지급", 0, action -> {
            Player player = (Player) action.getSender();
            try {
                if (action.getArgs().length > 1) {

                    long amount = Long.parseLong(action.getArgs()[1]);
                    for (OfflinePlayer players : Bukkit.getOfflinePlayers()) {
                        Mileage money = new Mileage(players);

                        MileageRecord record = new MileageRecord();
                        record.setBefore(money.getMoney());

                        money.deposit(amount);

                        record.setAfter(money.getMoney());
                        record.playerRecord(player, players, amount, MileageRecord.Type.ADD);
                        StringData.sendMoney(player, players, amount);
                    }

                } else {
                    StringData.noneMoney(player);
                }
            } catch (NumberFormatException e) {
                StringData.noneMoney(player);
            }
        });

        cmd.action("지급", 0, action -> {
            Player player = (Player) action.getSender();
            try {
                if (action.getArgs().length > 1) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                    if (action.getArgs().length > 2) {
                        long amount = Long.parseLong(action.getArgs()[2]);

                        assert target != null;
                        Mileage money = new Mileage(target);
                        MileageRecord record = new MileageRecord();

                        record.setBefore(money.getMoney());
                        money.deposit(amount);
                        record.setAfter(money.getMoney());

                        record.playerRecord(player, target, amount, MileageRecord.Type.ADD);

                        StringData.sendMoney(player, target, amount);
                    } else {
                        StringData.noneMoney(player);
                    }
                } else {
                    StringData.nonePlayer(player);
                }
            } catch (NumberFormatException e) {
                StringData.noneMoney(player);
            }
        });

        cmd.action("출금", 0, action -> {
            Player player = (Player) action.getSender();
            try {
                if (action.getArgs().length > 1) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                    if (action.getArgs().length > 2) {
                        long amount = Long.parseLong(action.getArgs()[2]);

                        assert target != null;
                        Mileage money = new Mileage(target);

                        MileageRecord record = new MileageRecord();
                        record.setBefore(money.getMoney());
                        money.withdraw(amount);
                        record.setAfter(money.getMoney());
                        record.playerRecord(player, target, amount, MileageRecord.Type.REMOVE);

                        StringData.removeMoney(player, target, amount);
                    } else {
                        StringData.noneMoney(player);
                    }
                } else {
                    StringData.nonePlayer(player);
                }
            } catch (NumberFormatException e) {
                StringData.noneMoney(player);
            }
        });
        cmd.action("설정", 0, action -> {
            Player player = (Player) action.getSender();
            try {
                if (action.getArgs().length > 1) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);
                    if (action.getArgs().length > 2) {
                        long amount = Long.parseLong(action.getArgs()[2]);

                        assert target != null;
                        Mileage money = new Mileage(target);
                        MileageRecord record = new MileageRecord();
                        record.setBefore(money.getMoney());
                        money.setMoney(amount);
                        record.setAfter(money.getMoney());
                        record.playerRecord(player, target, amount, MileageRecord.Type.SET);

                        StringData.setMoney(player, target, amount);
                    } else {
                        StringData.noneMoney(player);
                    }
                } else {
                    StringData.nonePlayer(player);
                }
            } catch (NumberFormatException e) {
                StringData.noneMoney(player);
            }
        });
        cmd.action("확인", 0, action -> {
            Player player = (Player) action.getSender();
            try {
                if (action.getArgs().length > 1) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(action.getArgs()[1]);

                    assert target != null;
                    StringData.targetMoney(player, target);

                } else {
                    StringData.nonePlayer(player);
                }
            } catch (NumberFormatException e) {
                StringData.noneMoney(player);
            }
        });
        cmd.action("초기화", 0, action -> {
            Player player = (Player) action.getSender();
            try {
                if (action.getArgs().length > 1) {
                    Player target = Bukkit.getPlayer(action.getArgs()[1]);

                    assert target != null;
                    Mileage money = new Mileage(target);
                    MileageRecord record = new MileageRecord();
                    record.setBefore(money.getMoney());
                    money.setMoney(0);
                    record.setAfter(0);
                    record.playerRecord(player, target, 0, MileageRecord.Type.RESET);

                    StringData.resetMoney(target, target, 0);

                } else {
                    StringData.nonePlayer(player);
                }
            } catch (NumberFormatException e) {
                StringData.noneMoney(player);
            }
        });
    }
}
