package net.skyexcel.server.chatchannel.cmd;

import net.skyexcel.server.chatchannel.data.ChatChannel;
import net.skyexcel.server.skyblock.data.player.SkyBlockPlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChatTab implements TabCompleter {
    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        List<String> result = new ArrayList<>();
        if (sender instanceof Player player) {
            if (args.length == 1) {
                SkyBlockPlayerData skyBlockPlayerData = new SkyBlockPlayerData(player);
                if (skyBlockPlayerData.hasIsland()) {
                    result.add(ChatChannel.SKYBLOCK.getName());
                } else {
                    result = List.of(ChatChannel.GLOBAL.getName(), ChatChannel.LOCAL.getName());

                }
            }
        }


        return result;
    }
}
