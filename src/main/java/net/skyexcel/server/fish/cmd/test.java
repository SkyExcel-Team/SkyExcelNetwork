package net.skyexcel.server.fish.cmd;

import net.skyexcel.server.fish.data.FishType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class test implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (args.length > 1) {
            Player player = (Player) sender;
            FishType fishType = FishType.GOLIATHGROUPER;
            player.getInventory().addItem(fishType.item(1));

        }

        return false;
    }
}
