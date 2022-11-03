package net.skyexcel.server.playtime.cmd;

import net.skyexcel.server.playtime.data.PlayTime;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayTimeCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length >= 1) {
                if (player.isOp()) {
                    Player target = Bukkit.getPlayer(args[0]);

                    assert target != null;
                    PlayTime playTime = new PlayTime(target);
                    player.sendMessage(playTime.translate());

                }
            } else {
                PlayTime playTime = new PlayTime(player);
                player.sendMessage("");
            }
        }

        return false;
    }
}
