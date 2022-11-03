package net.skyexcel.server.fish.cmd;

import net.skyexcel.server.fish.data.FishData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FishCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            if(args.length > 1){
                FishData fishData = new FishData(player);
                switch (args[0]){
                    case "보관함":
                        break;
                }
            }
        }
        return false;
    }
}
