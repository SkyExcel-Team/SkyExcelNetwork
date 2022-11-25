package net.skyexcel.server.quest.cmd;

import net.skyexcel.server.quest.data.QuestData;
import net.skyexcel.server.quest.gui.gui.QuestCheckGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QuestCmd implements CommandExecutor {
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

        if (sender instanceof Player player) {
            if (args.length > 0) {
                if ("초기화".equalsIgnoreCase(args[0])) {
                    if (player.isOp()) {
                        if (args.length > 1) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            QuestData questData = new QuestData(target);
                            questData.resetQuest();
                        }

                    }

                }
            } else {
                QuestCheckGUI questCheckGUI = new QuestCheckGUI();
                questCheckGUI.open(player);
            }
        }

        return false;
    }
}
