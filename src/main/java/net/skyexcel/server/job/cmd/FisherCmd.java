package net.skyexcel.server.job.cmd;

import net.skyexcel.server.job.data.Job;
import net.skyexcel.server.job.data.JobType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FisherCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            Job job = new Job(player);
            if(job.getType().equals(JobType.FISHERMAN)){
                
            }
        }

        return false;
    }
}
