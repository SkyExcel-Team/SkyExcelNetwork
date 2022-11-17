package net.skyexcel.server.job.cmd;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobData;
import net.skyexcel.server.job.data.JobType;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.job.gui.JobGUI;
import net.skyexcel.server.job.gui.JobSelectGUI;
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
                        job.setJobType(JobType.FISHERMAN);

                        switch (job.getType()) {
                            case FARM -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.farm();
                            }
                            case FISHERMAN -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.fish();
                                JobData.gui.put(player.getUniqueId(), jobGUI);
                            }
                            case MINEWORKER -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.mineWork();
                            }

                        }
                    }
                    case "초기화" -> {


                    }
                    case "선택" -> {

                        JobSelectGUI jobSelectGUI = new JobSelectGUI();
                        jobSelectGUI.open(player);
                        JobData.selectGUI.put(player.getUniqueId(), jobSelectGUI);
                    }
                }
            }
        }


        return false;
    }
}
