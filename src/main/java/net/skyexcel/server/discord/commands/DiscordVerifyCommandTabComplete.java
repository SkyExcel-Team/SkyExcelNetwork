package net.skyexcel.server.discord.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DiscordVerifyCommandTabComplete implements TabCompleter {


    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> completions = new ArrayList<>();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (player.isOp()) {
                    completions.add("리로드");
                }

                completions.add("발급");
            }
        }
        return completions;
    }
}
