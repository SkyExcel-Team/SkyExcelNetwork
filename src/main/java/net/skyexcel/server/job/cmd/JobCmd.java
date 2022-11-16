package net.skyexcel.server.job.cmd;

import net.skyexcel.server.job.data.Job;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JobCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            if(args.length > 0){
                Job job = new Job(player);
                switch (args[0]){
                    case "보기" ->{
                    }
                    case "초기화"->{


                    }
                }
            }
        }



        return false;
    }
}
