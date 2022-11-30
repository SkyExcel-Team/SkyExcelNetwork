package net.skyexcel.server.chatchannel.cmd;

import net.skyexcel.server.chatchannel.data.ChatData;
import net.skyexcel.server.chatchannel.data.ChatPlayerData;
import net.skyexcel.server.chatchannel.gui.ChatLogGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatCmd implements CommandExecutor {


    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                switch (args[0]) {
                    case "관리" -> {
                        if (args.length > 1) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            ChatLogGUI chatLogGUI = new ChatLogGUI(target);
                            chatLogGUI.open(player);
                            ChatPlayerData.chatLogGUIMap.put(player.getUniqueId(), chatLogGUI);
                        } else {
                            player.sendMessage("잘못된 명령어 입니다.");
                        }


                    }
                    case "뮤트" -> {
                        if (args.length > 1) {

                        } else {
                            player.sendMessage("잘못된 명령어 입니다.");
                        }
                    }
                }

            }
        }
        return false;
    }
}
