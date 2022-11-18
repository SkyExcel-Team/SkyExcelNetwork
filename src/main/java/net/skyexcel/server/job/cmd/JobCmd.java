package net.skyexcel.server.job.cmd;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.job.gui.JobGUI;
import net.skyexcel.server.job.gui.JobSelectGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JobCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
                Job job = new Job(player);
                switch (args[0]) {
                    case "보기" -> {

                        switch (job.getType()) {
                            case FARM -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.farm();
                                JobData.gui.put(player.getUniqueId(), jobGUI);
                            }
                            case FISHERMAN -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.fish();
                                JobData.gui.put(player.getUniqueId(), jobGUI);
                            }
                            case MINEWORKER -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.mineWork();
                                JobData.gui.put(player.getUniqueId(), jobGUI);
                            }

                        }
                    }
                    case "초기화" -> {
                        if (player.isOp()) {
                            if (args.length > 1) {
                                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                                Job target = new Job(offlinePlayer);
                                target.reset();
                                player.sendMessage(offlinePlayer.getName() + " 님의 직업을 초기화함ㅋ");
                            }
                        } else {
                            player.sendMessage("오피아님ㅅㄱ");
                        }

                    }
                    case "선택" -> {
                        if (!job.hasJob()) {
                            JobSelectGUI jobSelectGUI = new JobSelectGUI();
                            jobSelectGUI.open(player);
                            JobData.selectGUI.put(player.getUniqueId(), jobSelectGUI);
                        } else {
                            player.sendMessage("이미 직업 있음 ㅅㄱ");
                        }

                    }
                }
            }
        }


        return false;
    }
}
