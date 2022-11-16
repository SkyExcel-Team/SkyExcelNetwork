package net.skyexcel.server.job.cmd;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.type.Farmer;
import net.skyexcel.server.job.gui.JobGUI;
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
                            }
                            case FISHERMAN -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.fish();
                            }
                            case MINEWORKER -> {
                                JobGUI jobGUI = new JobGUI(player);
                                jobGUI.mineWork();
                            }

                        }
                    }
                    case "초기화" -> {


                    }
                }
            }
        }


        return false;
    }
}
