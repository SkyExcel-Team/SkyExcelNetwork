package net.skyexcel.server.fish.cmd;

import net.skyexcel.server.fish.SkyExcelNetworkFishMain;
import net.skyexcel.server.fish.data.FishData;
import net.skyexcel.server.fish.data.FishStatus;
import net.skyexcel.server.fish.runnable.CoolTime;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

public class FishGameCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
//                FishData fishData = new FishData(player);
                switch (args[0]) {
                    case "강제시작":
                        if (player.isOp()) {
                            SkyExcelNetworkFishMain.status = FishStatus.Start;

                            Bukkit.getOnlinePlayers().forEach(players -> {
                                players.sendMessage("낚시대회를 시작 합니다! 15분간 낚시를 진행 해 주세요!");
                                CoolTime coolTime = new CoolTime(players);
                                coolTime.cast();
                            });
                        }
                        break;

                    case "참여":

                        break;
                    case "시간":

                        break;

                }
            }
        }
        return false;
    }
}
